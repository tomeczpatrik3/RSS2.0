import { Component, OnInit } from "@angular/core";
import { ReservationEvent } from "../../../../shared/models/ReservationEvent";
import { EventApiService } from "../../../../shared/services/api/event.api.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { ReservationType } from "../../../../shared/enums/ReservationType";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-events-table",
  templateUrl: "./events-table.component.html",
  styleUrls: ["./events-table.component.css"]
})
export class EventsTableComponent implements OnInit {
  reservationEvents: ReservationEvent[];

  constructor(
    private eventService: EventApiService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.eventService
      .getEvents()
      .subscribe(events => (this.reservationEvents = events));
  }

  openPopup(event: ReservationEvent) {
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
