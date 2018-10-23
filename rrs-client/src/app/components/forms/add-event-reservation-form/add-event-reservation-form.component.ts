import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  FormGroup,
  Validators,
  FormBuilder
} from "@angular/forms";
import { ValidatorService } from "../../../services/validator.service";
import { AddReservation } from "../add-reservaion";
import { AuthService } from "../../../authentication/auth.service";
import { ClassroomService } from "../../../services/classroom.service";
import { SubjectService } from "../../../services/subject.service";
import { EventReservationService } from "../../../services/event-reservation.service";
import { BuildingService } from "../../../services/building.service";
import { SemesterService } from "../../../services/semester.service";
import { DialogService } from "../../../services/dialog.service";
import { Router } from "@angular/router";
import { EventReservation } from "../../../models/EventReservation";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../config/message-constants.config";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { TextUtils } from "../../../utils/text-utils";
import { UniqueEventNameValidator } from "../../../directives/unique-event-name.directive";
import { TakenBuildingNameValidator } from "../../../directives/taken-building-name.directive";
import { timeValidator } from "../../../directives/time.directive";

@Component({
  selector: "app-add-event-reservation-from",
  templateUrl: "./add-event-reservation-form.component.html",
  styleUrls: ["./add-event-reservation-form.component.css"]
})
export class AddEventReservationFormComponent extends AddReservation {
  reservationForm: FormGroup;

  ngOnInit() {
    this.reservationForm = new FormGroup(
      {
        name: new FormControl("", {
          validators: [
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(30)
          ],
          asyncValidators: this.eventValidator.validate.bind(
            this.eventValidator
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
        date: new FormControl("", [Validators.required]),
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

  get name() {
    return this.reservationForm.get("name");
  }
  get building() {
    return this.reservationForm.get("building");
  }
  get room() {
    return this.reservationForm.get("room");
  }
  get date() {
    return this.reservationForm.get("date");
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
    private eventReservationService: EventReservationService,
    private eventValidator: UniqueEventNameValidator,
    private buildingValidator: TakenBuildingNameValidator
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

  formToReservation() {
    return EventReservation.build(
      this.authService.getUsername(),
      this.building.value,
      this.room.value,
      this.note.value,
      this.name.value,
      this.date.value,
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
    this.eventReservationService
      .createEventReservation(this.formToReservation())
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
}