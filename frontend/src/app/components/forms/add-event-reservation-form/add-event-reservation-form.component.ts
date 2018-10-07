import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ValidatorService } from '../../../services/validator.service';
import { AddReservation } from '../add-reservaion';
import { AuthService } from '../../../authentication/auth.service';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { EventReservationService } from '../../../services/event-reservation.service';
import { BuildingService } from '../../../services/building.service';
import { SemesterService } from '../../../services/semester.service';
import { DialogService } from '../../../services/dialog.service';
import { Router } from '@angular/router';
import { EventReservation } from '../../../models/EventReservation';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-add-event-reservation-from',
  templateUrl: './add-event-reservation-form.component.html',
  styleUrls: ['./add-event-reservation-form.component.css']
})
export class AddEventReservationFormComponent extends AddReservation {

  /**
   * Az egyes formcontrollok:
   */
  name = new FormControl('', [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(30)
  ]);

  building = new FormControl('', [
    Validators.required
  ]);

  room = new FormControl('', [
    Validators.required
  ]);

  date = new FormControl('', [
    Validators.required
  ]);

  startTime = new FormControl('', [
    Validators.required,
    this.validatorService.isTime,
    Validators.minLength(5),
    Validators.maxLength(5)
  ]);

  endTime = new FormControl('', [
    Validators.required,
    this.validatorService.isTime,
    Validators.minLength(5),
    Validators.maxLength(5)
  ]);

  note = new FormControl('', []);

  //-------------------------------

  /**
   * Formgroup felépítése a formcontrollokból:
   */
  reservationForm: FormGroup = this.builder.group({
    name: this.name,
    building: this.building,
    room: this.room,
    date: this.date,
    startTime: this.startTime,
    endTime: this.endTime,
    note: this.note
  });

  constructor(
    protected authService:        AuthService,
    protected classroomService:   ClassroomService,
    protected subjectService:     SubjectService,
    protected eventReservationService: EventReservationService,
    protected buildingService:    BuildingService,
    protected semesterService:    SemesterService,
    protected builder:            FormBuilder,
    protected dialogService:      DialogService,
    protected validatorService:   ValidatorService,
    protected router:             Router
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

  protected formToReservation() {
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
  protected addReservation() {
    console.log(this.formToReservation());

    this.eventReservationService.createEventReservation(this.formToReservation()).subscribe(
      res => console.log(res),
      error => {
        this.dialogService.openDialog("Foglalás hozzáadása:", this.dialogService.addBr(error.error), InfoDialogComponent);
      },
      () => this.dialogService.openDialog("Foglalás hozzáadása:", "Foglalás rögítve, elbírálás alá került!", InfoDialogComponent)
    );    
  }
}
