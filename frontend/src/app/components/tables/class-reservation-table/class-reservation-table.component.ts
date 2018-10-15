import { Component, OnInit, Input } from "@angular/core";
import { ClassReservationService } from "../../../services/class-reservation.service";
import { ClassReservation } from "../../../models/ClassReservation";
import { DialogService } from "../../../services/dialog.service";
import { FormType } from "../../../enums/FormType";
import { EditDialogComponent } from "../../dialogs/edit-dialog/edit-dialog.component";

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
    private dialogService: DialogService) {}

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
    this.dialogService.openFormDialog(
      "Foglalás szerkesztése:",
      FormType.EDIT_CLASS_RESERVATION_FORM,
      id,
      EditDialogComponent
    );
  }
}
