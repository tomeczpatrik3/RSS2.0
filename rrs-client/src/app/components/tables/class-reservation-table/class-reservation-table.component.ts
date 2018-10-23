import { Component, OnInit, Input } from "@angular/core";
import { ClassReservationService } from "../../../services/class-reservation.service";
import { ClassReservation } from "../../../models/ClassReservation";
import { DialogService } from "../../../services/dialog.service";
import { FormType } from "../../../enums/FormType";
import { FormDialogComponent } from "../../dialogs/form-dialog/form-dialog.component";
import { AuthService } from "../../../authentication/auth.service";
import { Authorities } from "../../../config/authoritites.config";
import { Statuses } from "../../../config/statuses.config";

@Component({
  selector: "app-class-reservation-table",
  templateUrl: "./class-reservation-table.component.html",
  styleUrls: ["./class-reservation-table.component.css"]
})
export class ClassReservationTableComponent implements OnInit {
  @Input()
  user: string;
  @Input()
  pending: boolean;

  reservations: ClassReservation[];

  constructor(
    private classReservationService: ClassReservationService,
    private dialogService: DialogService,
    private authService: AuthService
  ) {}

  ngOnInit() {
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

  openPopup(id: number) {
    let formType: string;
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      formType = FormType.EDIT_CLASS_RESERVATION_FORM;
    } else {
      formType = FormType.OBSERVE_CLASS_RESERVATION_FORM;
    }

    this.dialogService.openFormDialog(
      "Foglalás szerkesztése:",
      formType,
      id,
      FormDialogComponent
    );
  }
}
