import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationsDataService } from "../../event-reservations.data.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";
import { AuthService } from "../../../../shared/services/auth.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { patterns } from "../../../../shared/utils/patterns";

@Component({
  selector: "app-edit-event-reservation-form",
  templateUrl: "./edit-event-reservation-form.component.html",
  styleUrls: ["./edit-event-reservation-form.component.css"]
})
export class EditEventReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  /*Ha admin szerkeszt*/
  isAdmin: boolean;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: EventReservation;

  /*DateTime pattern */
  dateTimePattern: string = patterns.dateTime;

  constructor(
    private eventReservationService: EventReservationsDataService,
    private dialogService: DialogService,
    private authService: AuthService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A megfelelő foglalás betöltése, valamint a
   * kezdés és befejezés átkonvertálása
   */
  ngOnInit() {
    this.eventReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
      this.model.startDate = this.model.startDate.substring(0, 16);
      this.model.endDate = this.model.endDate.substring(0, 16);
    });

    this.isAdmin = this.authService.hasAuthority(Authorities.ROLE_ADMIN);
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    if (this.isAdmin) {
      this.eventReservationService
        .update(this.reservationID, this.model)
        .subscribe(
          () => this.submitEvent.next(true),
          error =>
            this.dialogService.openDialog(
              "Foglalás szerkesztése:",
              TextUtils.addBreaks(error.error),
              ErrorDialogComponent
            )
        );
    } else {
      this.eventReservationService
        .updateOwnById(this.reservationID, this.model)
        .subscribe(
          () => this.submitEvent.next(true),
          error =>
            this.dialogService.openDialog(
              "Foglalás szerkesztése:",
              TextUtils.addBreaks(error.error),
              ErrorDialogComponent
            )
        );
    }
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }

  /**
   * A törlésért felelős függvény
   */
  onDelete() {
    this.eventReservationService
      .deleteById(this.reservationID)
      .subscribe(() => this.submitEvent.next(true));
  }
}
