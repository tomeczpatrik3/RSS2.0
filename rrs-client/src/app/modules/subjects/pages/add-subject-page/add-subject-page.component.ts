import { Component, OnInit, ViewChild } from "@angular/core";
import { AddSubjectFormComponent } from "../../components/add-subject-form/add-subject-form.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-subject-page",
  templateUrl: "./add-subject-page.component.html",
  styleUrls: ["./add-subject-page.component.css"]
})
export class AddSubjectPageComponent implements OnInit {
  @ViewChild(AddSubjectFormComponent)
  addSubjectFormComponent: AddSubjectFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addSubjectFormComponent.subjectForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
