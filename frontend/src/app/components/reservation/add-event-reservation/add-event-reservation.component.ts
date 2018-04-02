import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms/src/model';
import { Validators } from '@angular/forms/src/validators';
import { FormBuilder } from '@angular/forms/src/form_builder';
import { ValidatorService } from '../../../services/validator.service';

@Component({
  selector: 'app-add-event-reservation',
  templateUrl: './add-event-reservation.component.html',
  styleUrls: ['./add-event-reservation.component.css']
})
export class AddEventReservationComponent implements OnInit {

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
    private builder: FormBuilder,
    private validatorService: ValidatorService
  ) { }

  ngOnInit() {
  }

}
