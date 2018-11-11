import { Component, OnInit, Input } from "@angular/core";
import { ClassReservation } from "../../../../shared/models/ClassReservation";
import { ClassReservationsDataService } from "../../class-reservations.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";
import { Statuses } from "../../../../shared/config/statuses.config";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { MessageApiService } from "../../../../shared/services/api/message.api.service";
import { MessageType } from "../../../../shared/enums/MessageType";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-class-reservation-table",
  templateUrl: "./class-reservation-table.component.html",
  styleUrls: ["./class-reservation-table.component.css"]
})
export class ClassReservationTableComponent implements OnInit {
  /*A felhasználónév*/
  @Input()
  user: string;

  /*Függőben lévő foglalások?*/
  @Input()
  pending: boolean;

  /*A foglalások*/
  reservations: ClassReservation[];

  constructor(
    private classReservationService: ClassReservationsDataService,
    private messageService: MessageApiService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.refreshTable();
  }

  /**
   * A foglalás részleteinek megjlenítéséért felelős függvény
   * @param id A foglalás azonosítója
   */
  openDetails(id: number) {
    this.dialogService.openFormDialog(
      "Foglalás megtekintése:",
      FormType.OBSERVE_CLASS_RESERVATION_FORM,
      id,
      FormDialogComponent
    );
  }

  /**
   * A foglalás szerkesztéséért felelős függvény
   * @param id A foglalás azonosítója
   */  
  openEdit(id: number) {
    this.dialogService
      .openFormDialog(
        "Foglalás szerkesztése:",
        FormType.EDIT_CLASS_RESERVATION_FORM,
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
      this.classReservationService
        .getAccepted()
        .subscribe(res => (this.reservations = res));
    } else if (this.user && !this.pending) {
      this.classReservationService
        .findByUsername(this.user)
        .subscribe(res => (this.reservations = res));
    } else if (!this.user && this.pending) {
      this.classReservationService
        .getPending()
        .subscribe(res => (this.reservations = res));
    }
  }

  /**
   * Az elfogadásért felelős függvény
   * @param reservation A foglalás
   */
  accept(reservation: ClassReservation): void {
    this.classReservationService
      .setStatus(reservation.id, Statuses.ACCEPTED)
      .subscribe(
        () => {},
        error =>
          this.dialogService.openDialog(
            "Foglalás elfogadása:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
          ),
        () => {
          this.messageService
            .generateSystemMessage(
              reservation.username,
              reservation.id,
              MessageType.ACCEPT_MSG
            )
            .subscribe(
              () => this.refreshTable(),
              error =>
                this.dialogService.openDialog(
                  "Rendszerüzenet generálása:",
                  TextUtils.addBreaks(error.error),
                  ErrorDialogComponent
                )
            );
        }
      );
  }

  /**
   * Az elutasításért felelős függvény
   * @param reservation A foglalás
   */
  decline(reservation: ClassReservation): void {
    this.classReservationService
      .setStatus(reservation.id, Statuses.DECLINED)
      .subscribe(
        () => {},
        error =>
          this.dialogService.openDialog(
            "Foglalás elutasítása:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
          ),
        () => {
          this.messageService
            .generateSystemMessage(
              reservation.username,
              reservation.id,
              MessageType.DECLINE_MSG
            )
            .subscribe(
              () => this.refreshTable(),
              error =>
                this.dialogService.openDialog(
                  "Rendszerüzenet generálása:",
                  TextUtils.addBreaks(error.error),
                  ErrorDialogComponent
                )
            );
        }
      );
  }
}
