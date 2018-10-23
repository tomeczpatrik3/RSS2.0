import { Component, OnInit } from "@angular/core";
import { ReservationCalendarEvent } from "../../../models/ReservationCalendarEvent";
import { EventService } from "../../../services/event.service";
import { AuthService } from "../../../authentication/auth.service";
import { Authorities } from "../../../config/authoritites.config";
import { DialogService } from "../../../services/dialog.service";
import { FormType } from "../../../enums/FormType";
import { FormDialogComponent } from "../../dialogs/form-dialog/form-dialog.component";
import { ReservationType } from "../../../enums/ReservationType";

@Component({
  selector: "app-reservation-table",
  templateUrl: "./reservation-table.component.html",
  styleUrls: ["./reservation-table.component.css"]
})
export class ReservationTableComponent implements OnInit {
  reservationEvents: ReservationCalendarEvent[];

  constructor(
    private eventService: EventService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.eventService
      .getEvents()
      .subscribe(events => (this.reservationEvents = events));
  }

  openPopup(event: ReservationCalendarEvent) {
    let formType: string;
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      if (event.info.type == ReservationType.EVENT) {
        formType = FormType.EDIT_EVENT_RESERVATION_FORM;
      } else {
        formType = FormType.EDIT_CLASS_RESERVATION_FORM;
      }
    } else {
      if (event.info.type == ReservationType.EVENT) {
        formType = FormType.OBSERVE_EVENT_RESERVATION_FORM;
      } else {
        formType = FormType.OBSERVE_CLASS_RESERVATION_FORM;
      }
    }

    this.dialogService.openFormDialog(
      "Foglalás szerkesztése:",
      formType,
      event.info.id,
      FormDialogComponent
    );
  }
}
