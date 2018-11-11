import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { ClassReservation } from "../../../../shared/models/ClassReservation";
import { ClassReservationsDataService } from "../../class-reservations.data.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";
import { AuthService } from "../../../../shared/services/auth.service";
import { Authorities } from "../../../../shared/config/authoritites.config";

@Component({
  selector: "app-edit-class-reservation-form",
  templateUrl: "./edit-class-reservation-form.component.html",
  styleUrls: ["./edit-class-reservation-form.component.css"]
})
export class EditClassReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  /*Ha admin szerkeszt*/
  isAdmin: boolean;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: ClassReservation;

  constructor(
    private classReservationService: ClassReservationsDataService,
    private dialogService: DialogService,
    private authService: AuthService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A megfelelő foglalás betöltése
   */
  ngOnInit() {
    this.classReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
    });

    this.isAdmin = this.authService.hasAuthority(Authorities.ROLE_ADMIN);
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    if (this.isAdmin) {
      this.classReservationService
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
      this.classReservationService
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
    this.classReservationService
      .deleteById(this.reservationID)
      .subscribe(() => this.submitEvent.next(true));
  }
}
