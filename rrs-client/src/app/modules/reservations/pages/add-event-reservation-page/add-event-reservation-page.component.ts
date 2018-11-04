import { Component, OnInit, ViewChild } from "@angular/core";
import { AddEventReservationFormComponent } from "../../components/add-event-reservation-form/add-event-reservation-form.component";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";
import { DialogService } from "../../../../shared/services/dialog.service";

@Component({
  selector: "app-add-event-reservation-page",
  templateUrl: "./add-event-reservation-page.component.html",
  styleUrls: ["./add-event-reservation-page.component.css"]
})
export class AddEventReservationPageComponent implements OnInit {
  @ViewChild(AddEventReservationFormComponent)
  addEventReservationFormComponent: AddEventReservationFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addEventReservationFormComponent.reservationForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
