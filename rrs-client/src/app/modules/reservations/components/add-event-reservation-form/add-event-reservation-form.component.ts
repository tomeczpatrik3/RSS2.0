import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  FormGroup,
  Validators,
  FormBuilder
} from "@angular/forms";
import { ValidatorService } from "../../../../shared/services/validator.service";
import { AddReservation } from "../add-reservaion";
import { AuthService } from "../../../../shared/services/auth.service";
import { ClassroomApiService } from "../../../../shared/services/api/classroom.api.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Router } from "@angular/router";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { UniqueEventNameValidator } from "../../../../shared/directives/unique-event-name.directive";
import { TakenBuildingNameValidator } from "../../../../shared/directives/taken-building-name.directive";
import { timeValidator } from "../../../../shared/directives/time.directive";
import { SubjectApiService } from "../../../../shared/services/api/subject.api.service";
import { BuildingApiService } from "../../../../shared/services/api/building.api.service";
import { SemesterApiService } from "../../../../shared/services/api/semester.api.service";
import { EventReservationsDataService } from "../../event-reservations.data.service";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-event-reservation-form",
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
    protected classroomService: ClassroomApiService,
    protected subjectService: SubjectApiService,
    protected buildingService: BuildingApiService,
    protected semesterService: SemesterApiService,
    protected builder: FormBuilder,
    protected dialogService: DialogService,
    protected validatorService: ValidatorService,
    protected router: Router,
    private eventReservationService: EventReservationsDataService,
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
