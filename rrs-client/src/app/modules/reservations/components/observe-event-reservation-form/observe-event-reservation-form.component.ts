import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { UserApiService } from '../../../../shared/services/api/user.api.service';
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationApiService } from "../../../../shared/services/api/event-reservation.api.service";

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
    private eventReservationService: EventReservationApiService,
    private userService: UserApiService
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