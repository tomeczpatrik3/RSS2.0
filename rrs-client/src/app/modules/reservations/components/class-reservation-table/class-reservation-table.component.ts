import { Component, OnInit, Input } from "@angular/core";
import { ClassReservation } from "../../../../shared/models/ClassReservation";
import { ClassReservationsDataService } from "../../class-reservations.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

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
    private classReservationService: ClassReservationsDataService,
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
