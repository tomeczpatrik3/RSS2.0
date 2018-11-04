import { Component, OnInit, ViewChild } from "@angular/core";
import { AddSemesterReservationFormComponent } from "../../components/add-semester-reservation-form/add-semester-reservation-form.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-semester-reservation-page",
  templateUrl: "./add-semester-reservation-page.component.html",
  styleUrls: ["./add-semester-reservation-page.component.css"]
})
export class AddSemesterReservationPageComponent implements OnInit {
  @ViewChild(AddSemesterReservationFormComponent)
  addSemesterReservationFormComponent: AddSemesterReservationFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addSemesterReservationFormComponent.reservationForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
