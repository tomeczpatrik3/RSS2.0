import { Component, OnInit, Input } from "@angular/core";
import { EventReservation } from "../../../models/EventReservation";
import { EventReservationService } from "../../../services/event-reservation.service";
import { DialogService } from "../../../services/dialog.service";
import { EditEventReservationDialogComponent } from "../../dialogs/edit-event-reservation-dialog/edit-event-reservation-dialog.component";

@Component({
  selector: "app-event-reservation-table",
  templateUrl: "./event-reservation-table.component.html",
  styleUrls: ["./event-reservation-table.component.css"]
})
export class EventReservationTableComponent implements OnInit {
  @Input()
  user: string;
  reservations: EventReservation[];

  constructor(
    private eventReservationService: EventReservationService,
    private dialogService: DialogService
  ) {}

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

  openPopup(id: number) {
    this.dialogService.openFormDialog(
      "Foglalás szerkesztése:",
      id,
      EditEventReservationDialogComponent
    );
  }
}
