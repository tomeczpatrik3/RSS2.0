import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { EventReservation } from "../../../models/EventReservation";
import { EventReservationService } from "../../../services/event-reservation.service";
import { UserService } from "../../../services/user.service";

@Component({
  selector: "app-observe-event-reservation-form",
  templateUrl: "./observe-event-reservation-form.component.html",
  styleUrls: ["./observe-event-reservation-form.component.css"]
})
export class ObserveEventReservationFormComponent implements OnInit {
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  model: EventReservation;
  fullName: string;

  constructor(
    private eventReservationService: EventReservationService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.eventReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
      this.model.startDate = this.model.startDate.substring(0, 16);
      this.model.endDate = this.model.endDate.substring(0, 16);
      this.userService.getNameByUsername(this.model.username).subscribe(res => {
        this.fullName = res.name;
      });
    });
  }

  onExit() {
    this.submitEvent.next(false);
  }
}
