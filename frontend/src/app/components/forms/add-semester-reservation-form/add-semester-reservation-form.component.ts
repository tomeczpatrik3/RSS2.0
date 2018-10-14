import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";

import { AuthService } from "../../../authentication/auth.service";
import { ClassroomService } from "../../../services/classroom.service";
import { SubjectService } from "../../../services/subject.service";
import { ClassReservationService } from "../../../services/class-reservation.service";
import { BuildingService } from "../../../services/building.service";
import { SemesterService } from "../../../services/semester.service";
import { DialogService } from "../../../services/dialog.service";
import { ValidatorService } from "../../../services/validator.service";

import { Router } from "@angular/router";
import { AddReservation } from "../add-reservaion";
import { ClassReservation } from "../../../models/ClassReservation";
import { Semester } from "../../../models/Semester";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { Day } from "../../../enums/Day";
import { MessageConstants } from "../../../config/message-constants.config";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { Observable } from "rxjs";
import { TextUtils } from "../../../utils/text-utils";
import { TakenBuildingNameValidator } from "../../../directives/taken-building-name.directive";
import { TakenSemesterNameValidator } from "../../../directives/taken-semester-name.directive";
import { TakenSubjectCodeValidator } from "../../../directives/taken-subject-code.directive";
import { timeValidator } from "../../../directives/time.directive";

@Component({
  selector: "app-add-semester-reservation-form",
  templateUrl: "./add-semester-reservation-form.component.html",
  styleUrls: ["./add-semester-reservation-form.component.css"]
})
export class AddSemesterReservationFormComponent extends AddReservation {
  days: String[] = [Day[1], Day[2], Day[3], Day[4], Day[5], Day[6], Day[0]];

  selectedSemester: Semester;

  reservationForm: FormGroup;

  constructor(
    protected authService: AuthService,
    protected classroomService: ClassroomService,
    protected subjectService: SubjectService,
    protected buildingService: BuildingService,
    protected semesterService: SemesterService,
    protected builder: FormBuilder,
    protected dialogService: DialogService,
    protected validatorService: ValidatorService,
    protected router: Router,
    private classReservationService: ClassReservationService,
    private buildingValidator: TakenBuildingNameValidator,
    private semesterValidator: TakenSemesterNameValidator,
    private subjectValidator: TakenSubjectCodeValidator
  ) {
    super(
      authService,
      classroomService,
      subjectService,
      buildingService,
      semesterService,
      builder,
      dialogService,
      validatorService,
      router
    );
  }

  ngOnInit() {
    this.reservationForm = new FormGroup(
      {
        semester: new FormControl("", {
          validators: [Validators.required],
          asyncValidators: this.semesterValidator.validate.bind(
            this.semesterValidator
          ),
          updateOn: "blur"
        }),
        subject: new FormControl("", {
          validators: [Validators.required],
          asyncValidators: this.subjectValidator.validate.bind(
            this.subjectValidator
          ),
          updateOn: "blur"
        }),
        building: new FormControl("", {
          validators: [Validators.required],
          asyncValidators: this.buildingValidator.validate.bind(
            this.buildingValidator
          ),
          updateOn: "blur"
        }),
        room: new FormControl("", [Validators.required]),
        day: new FormControl("", [Validators.required]),
        startTime: new FormControl("", [
          Validators.required,
          this.validatorService.isTime,
          Validators.minLength(5),
          Validators.maxLength(5)
        ]),
        endTime: new FormControl("", [
          Validators.required,
          this.validatorService.isTime,
          Validators.minLength(5),
          Validators.maxLength(5)
        ]),
        note: new FormControl("", [])
      },
      { validators: timeValidator }
    );
  }

  get semester() {
    return this.reservationForm.get("semester");
  }
  get subject() {
    return this.reservationForm.get("subject");
  }
  get building() {
    return this.reservationForm.get("building");
  }
  get room() {
    return this.reservationForm.get("room");
  }
  get day() {
    return this.reservationForm.get("day");
  }
  get startTime() {
    return this.reservationForm.get("startTime");
  }
  get endTime() {
    return this.reservationForm.get("endTime");
  }
  get note() {
    return this.reservationForm.get("note");
  }

  protected formToReservation() {
    return ClassReservation.buildSemester(
      this.authService.getUsername(),
      this.building.value,
      this.room.value,
      this.note.value,
      this.selectedSemester,
      this.getSubjectCode(this.subject.value),
      this.day.value,
      this.startTime.value,
      this.endTime.value
    );
  }

  /**
   * Feliratkozunk, majd:
   * - hiba esetén jelzünk a hibát dialog segítségével
   * - siker esetén jelezzük a sikert dialog segítségével
   */
  protected addReservation() {
    this.classReservationService
      .createClassReservation(this.formToReservation())
      .subscribe(
        res => {},
        error => {
          this.dialogService.openDialog(
            "Foglalás hozzáadása:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
          );
        },
        () =>
          this.dialogService.openDialog(
            "Foglalás hozzáadása:",
            "Foglalás rögítve, elbírálás alá került!",
            InfoDialogComponent
          )
      );
    this.reservationForm.reset();
  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.reservationForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }

  selectSemester(semesterName: string) {
    this.selectedSemester = this.semesters.filter(
      semester => semester.name === semesterName
    )[0];
  }
}
