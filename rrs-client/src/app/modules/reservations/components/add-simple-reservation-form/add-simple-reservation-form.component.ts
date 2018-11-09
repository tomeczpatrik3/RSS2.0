import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";

import { Router } from "@angular/router";
import { AddReservation } from "../add-reservaion";
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
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-add-simple-reservation-form",
  templateUrl: "./add-simple-reservation-form.component.html",
  styleUrls: ["./add-simple-reservation-form.component.css"]
})
export class AddSimpleReservationFormComponent extends AddReservation {
  /*Az űrlap*/
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
        date: new FormControl("", [
          Validators.required,
          this.validatorService.isFutureDate
        ]),
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
  get subject() {
    return this.reservationForm.get("subject");
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

  /**
   * Az űrlap foglalássá konvertálását megvalósító függvény
   */
  formToReservation() {
    return ClassReservation.buildSimple(
      this.authService.getUsername(),
      this.building.value,
      this.room.value,
      this.note.value,
      this.getSubjectCode(this.subject.value),
      this.date.value,
      this.startTime.value,
      this.endTime.value
    );
  }

  /**
   * A foglalás létrehozását megvalósító függvény
   */
  addReservation() {
    this.classReservationService
      .createClassReservation(this.formToReservation())
      .subscribe(
        () => {},
        error => {
          this.dialogService.openDialog(
            "Foglalás hozzáadása:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
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
}
