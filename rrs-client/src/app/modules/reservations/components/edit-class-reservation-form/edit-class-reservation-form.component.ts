import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ClassReservation } from '../../../../shared/models/ClassReservation';
import { ClassReservationsDataService } from "../../class-reservations.data.service";

@Component({
  selector: 'app-edit-class-reservation-form',
  templateUrl: './edit-class-reservation-form.component.html',
  styleUrls: ['./edit-class-reservation-form.component.css']
})
export class EditClassReservationFormComponent implements OnInit {
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  model: ClassReservation;

  constructor(private classReservationService: ClassReservationsDataService) {}

  ngOnInit() {
    this.classReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
    });
  }

  onSubmit() {
    this.submitEvent.next(true);
  }

  onExit() {
    this.submitEvent.next(false);
  }
}