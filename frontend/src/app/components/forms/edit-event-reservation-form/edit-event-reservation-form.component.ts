import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { EventReservation } from "../../../models/EventReservation";
import { EventReservationService } from "../../../services/event-reservation.service";

@Component({
  selector: "app-edit-event-reservation-form",
  templateUrl: "./edit-event-reservation-form.component.html",
  styleUrls: ["./edit-event-reservation-form.component.css"]
})
export class EditEventReservationFormComponent implements OnInit {
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  model: EventReservation;

  constructor(private eventReservationService: EventReservationService) {}

  ngOnInit() {
    this.eventReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
      this.model.startDate = this.model.startDate.substring(0, 16);
      this.model.endDate = this.model.endDate.substring(0, 16);
    });
  }

  onSubmit() {
    this.submitEvent.next(true);
  }

  onExit() {
    this.submitEvent.next(false);
  }
}
