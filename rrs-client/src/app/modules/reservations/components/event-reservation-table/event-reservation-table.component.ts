import { Component, OnInit, Input } from "@angular/core";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationsDataService } from "../../event-reservations.data.service";
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
  /*A felhasználónév*/
  @Input()
  user: string;
  /*Függőben lévő foglalások?*/
  @Input()
  pending: boolean;

  /*A foglalások*/
  reservations: EventReservation[];

  constructor(
    private eventReservationService: EventReservationsDataService,
    private dialogService: DialogService,
    private authService: AuthService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.refreshTable();
  }

  /**
   * A megfelelő dialógus megjlenítéséért felelős függvény
   * @param id A foglalás azonosítója
   */
  openPopup(id: number) {
    let formType: string;
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      formType = FormType.EDIT_EVENT_RESERVATION_FORM;
    } else {
      formType = FormType.OBSERVE_EVENT_RESERVATION_FORM;
    }

    this.dialogService
      .openFormDialog(
        "Foglalás szerkesztése:",
        formType,
        id,
        FormDialogComponent
      )
      .subscribe(result => {
        if (result == true) {
          this.refreshTable();
        }
      });
  }

  /**
   * A táblázat frissítéséért felelős függvény
   */
  refreshTable(): void {
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
}
