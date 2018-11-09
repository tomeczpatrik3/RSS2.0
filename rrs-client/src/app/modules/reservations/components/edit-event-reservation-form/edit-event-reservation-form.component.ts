import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationsDataService } from "../../event-reservations.data.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-edit-event-reservation-form",
  templateUrl: "./edit-event-reservation-form.component.html",
  styleUrls: ["./edit-event-reservation-form.component.css"]
})
export class EditEventReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: EventReservation;

  constructor(
    private eventReservationService: EventReservationsDataService,
    private dialogService: DialogService
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
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
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
      .subscribe(result => this.submitEvent.next(true));
  }
}
