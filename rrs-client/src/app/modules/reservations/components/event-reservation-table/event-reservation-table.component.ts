import { Component, OnInit, Input } from "@angular/core";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationApiService } from "../../../../shared/services/api/event-reservation.api.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-event-reservation-table",
  templateUrl: "./event-reservation-table.component.html",
  styleUrls: ["./event-reservation-table.component.css"]
})
export class EventReservationTableComponent implements OnInit {
  @Input()
  user: string;
  @Input()
  pending: boolean;

  reservations: EventReservation[];

  constructor(
    private eventReservationService: EventReservationApiService,
    private dialogService: DialogService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    if (!this.user && !this.pending) {
      //Null, empty
      this.eventReservationService
        .getAccepted()
        .subscribe(res => (this.reservations = res));
    } else if (this.user && !this.pending) {
      this.eventReservationService
        .findByUsername(this.user)
        .subscribe(res => (this.reservations = res));
    } else if (!this.user && this.pending) {
      this.eventReservationService
        .getPending()
        .subscribe(res => (this.reservations = res));
    }
  }

  openPopup(id: number) {
    let formType: string;
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      formType = FormType.EDIT_EVENT_RESERVATION_FORM;
    } else {
      formType = FormType.OBSERVE_EVENT_RESERVATION_FORM;
    }

    this.dialogService.openFormDialog(
      "Foglalás szerkesztése:",
      formType,
      id,
      FormDialogComponent
    );
  }
}
