import { Component, OnInit, ViewChild } from "@angular/core";
import { AddBuildingFormComponent } from "../../components/add-building-form/add-building-form.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-building-page",
  templateUrl: "./add-building-page.component.html",
  styleUrls: ["./add-building-page.component.css"]
})
export class AddBuildingPageComponent implements OnInit {
  @ViewChild(AddBuildingFormComponent)
  addBuildingFormComponent: AddBuildingFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addBuildingFormComponent.buildingForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
