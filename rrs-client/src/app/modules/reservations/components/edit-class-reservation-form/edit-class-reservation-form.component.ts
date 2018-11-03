import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { ClassReservation } from "../../../../shared/models/ClassReservation";
import { ClassReservationsDataService } from "../../class-reservations.data.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../../shared/services/dialog.service";

@Component({
  selector: "app-edit-class-reservation-form",
  templateUrl: "./edit-class-reservation-form.component.html",
  styleUrls: ["./edit-class-reservation-form.component.css"]
})
export class EditClassReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: ClassReservation;

  constructor(
    private classReservationService: ClassReservationsDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A megfelelő foglalás betöltése
   */
  ngOnInit() {
    this.classReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.classReservationService
      .update(this.reservationID, this.model)
      .subscribe(
        () => this.submitEvent.next(true),
        error =>
          this.dialogService.openDialog(
            "Foglalás szerkesztése:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
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
    this.classReservationService
      .deleteById(this.reservationID)
      .subscribe(result => this.submitEvent.next(true));
  }
}
