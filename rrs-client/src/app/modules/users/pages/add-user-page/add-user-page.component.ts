import { Component, OnInit, ViewChild } from "@angular/core";
import { AddUserFormComponent } from "../../components/add-user-form/add-user-form.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-user-page",
  templateUrl: "./add-user-page.component.html",
  styleUrls: ["./add-user-page.component.css"]
})
export class AddUserPageComponent implements OnInit {
  @ViewChild(AddUserFormComponent)
  addUserFormComponent: AddUserFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addUserFormComponent.userForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
