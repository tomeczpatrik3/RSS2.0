import { Component, OnInit, Input } from "@angular/core";
import { EventReservation } from "../../../models/EventReservation";
import { EventReservationService } from "../../../services/event-reservation.service";
import { DialogService } from "../../../services/dialog.service";
import { EditDialogComponent } from "../../dialogs/edit-dialog/edit-dialog.component";
import { FormType } from "../../../enums/FormType";
import { AuthService } from "../../../authentication/auth.service";
import { Authorities } from "../../../config/authoritites.config";

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
    private dialogService: DialogService,
    private authService: AuthService
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
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService.openFormDialog(
        "Foglalás szerkesztése:",
        FormType.EDIT_EVENT_RESERVATION_FORM,
        id,
        EditDialogComponent
      );
    }
  }
}
