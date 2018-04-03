import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms/src/model';
import { Validators } from '@angular/forms/src/validators';
import { FormBuilder } from '@angular/forms/src/form_builder';
import { ValidatorService } from '../../../services/validator.service';
import { AddReservationBaseComponent } from '../add-reservaion-base.component';
import { AuthService } from '../../../authentication/auth.service';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { ReservationService } from '../../../services/reservation.service';
import { BuildingService } from '../../../services/building.service';
import { SemesterService } from '../../../services/semester.service';
import { DialogService } from '../../../services/dialog.service';
import { Router } from '@angular/router';
import { EventReservation } from '../../../models/EventReservation';

@Component({
  selector: 'app-add-event-reservation',
  templateUrl: './add-event-reservation.component.html',
  styleUrls: ['./add-event-reservation.component.css']
})
export class AddEventReservationComponent extends AddReservationBaseComponent {

  /**
   * Az egyes formcontrollok:
   */
  eventName = new FormControl('', [
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
    eventName: this.eventName,
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
    protected reservationService: ReservationService,
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
      reservationService,
      buildingService,
      semesterService,
      builder,
      dialogService,
      validatorService,
      router
    );
  }

  protected formToReservation() {
    return new EventReservation(
      this.authService.getUsername(),
      this.building.value,
      this.room.value,
      this.note.value,
      this.eventName.value,
      this.date.value,
      this.startTime.value,
      this.endTime.value
    )
  }
}
