import { Component, OnInit, Input } from "@angular/core";
import { EventReservation } from "../../../models/EventReservation";
import { EventReservationService } from "../../../services/event-reservation.service";

@Component({
  selector: "app-event-reservation-table",
  templateUrl: "./event-reservation-table.component.html",
  styleUrls: ["./event-reservation-table.component.css"]
})
export class EventReservationTableComponent implements OnInit {
  @Input()
  user: string;
  reservations: EventReservation[];

  constructor(private eventReservationService: EventReservationService) {}

  ngOnInit() {
    if (!this.user) {
      //Null, empty
      this.eventReservationService
        .getAccepted()
        .subscribe(res => (this.reservations = res));
    } else {
      this.eventReservationService
        .findByUsername(this.user)
        .subscribe(res => (this.reservations = res));
    }
  }
}
