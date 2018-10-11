import { Component, OnInit, Input } from "@angular/core";
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

  model: EventReservation;

  constructor(public eventReservationService: EventReservationService) {}

  ngOnInit() {
    this.eventReservationService.findById(this.reservationID).subscribe(res => {
      console.log(this.reservationID);
      console.log(res);
      this.model = res;
    });
  }

  onSubmit() {
    console.log(this.model);
    //Update the reservation
  }
}
