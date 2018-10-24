import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";


import { Router } from "@angular/router";
import { AddReservation } from "../add-reservaion";
import { Observable } from "rxjs";
import { Day } from "../../../../shared/enums/Day";
import { Semester } from "../../../../shared/models/Semester";
import { AuthService } from "../../../../shared/services/auth.service";
import { ClassroomApiService } from "../../../../shared/services/api/classroom.api.service";
import { SubjectApiService } from "../../../../shared/services/api/subject.api.service";
import { BuildingApiService } from "../../../../shared/services/api/building.api.service";
import { SemesterApiService } from "../../../../shared/services/api/semester.api.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ValidatorService } from "../../../../shared/services/validator.service";
import { ClassReservationsDataService } from "../../class-reservations.data.service";
import { TakenBuildingNameValidator } from "../../../../shared/directives/taken-building-name.directive";
import { TakenSemesterNameValidator } from "../../../../shared/directives/taken-semester-name.directive";
import { TakenSubjectCodeValidator } from "../../../../shared/directives/taken-subject-code.directive";
import { timeValidator } from "../../../../shared/directives/time.directive";
import { ClassReservation } from "../../../../shared/models/ClassReservation";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

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
    protected classroomService: ClassroomApiService,
    protected subjectService: SubjectApiService,
    protected buildingService: BuildingApiService,
    protected semesterService: SemesterApiService,
    protected builder: FormBuilder,
    protected dialogService: DialogService,
    protected validatorService: ValidatorService,
    protected router: Router,
    private classReservationService: ClassReservationsDataService,
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

  formToReservation() {
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
  addReservation() {
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
