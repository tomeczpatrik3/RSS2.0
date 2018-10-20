import { Component, OnInit, Input } from "@angular/core";
import { ClassReservationService } from "../../../services/class-reservation.service";
import { ClassReservation } from "../../../models/ClassReservation";
import { DialogService } from "../../../services/dialog.service";
import { FormType } from "../../../enums/FormType";
import { FormDialogComponent } from "../../dialogs/form-dialog/form-dialog.component";
import { AuthService } from "../../../authentication/auth.service";
import { Authorities } from "../../../config/authoritites.config";

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
      //Null, empty
      this.classReservationService
        .getAccepted()
        .subscribe(res => (this.reservations = res));
    } else {
      this.classReservationService
        .findByUsername(this.user)
        .subscribe(res => (this.reservations = res));
    }
  }

  openPopup(id: number) {
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService.openFormDialog(
        "Foglalás szerkesztése:",
        FormType.EDIT_CLASS_RESERVATION_FORM,
        id,
        FormDialogComponent
      );
    }
    else {
      this.dialogService.openFormDialog(
        "Foglalás szerkesztése:",
        FormType.OBSERVE_CLASS_RESERVATION_FORM,
        id,
        FormDialogComponent
      );      
    }
  }
}
