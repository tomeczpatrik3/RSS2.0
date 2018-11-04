import { Component, OnInit, ViewChild } from "@angular/core";
import { AddSimpleReservationFormComponent } from "../../components/add-simple-reservation-form/add-simple-reservation-form.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-simple-reservation-page",
  templateUrl: "./add-simple-reservation-page.component.html",
  styleUrls: ["./add-simple-reservation-page.component.css"]
})
export class AddSimpleReservationPageComponent implements OnInit {
  @ViewChild(AddSimpleReservationFormComponent)
  addSimpleReservationFormComponent: AddSimpleReservationFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addSimpleReservationFormComponent.reservationForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
