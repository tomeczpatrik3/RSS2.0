import { Component, OnInit, ViewChild } from "@angular/core";
import { AddClassroomFormComponent } from "../../components/add-classroom-form/add-classroom-form.component";
import { Observable } from "rxjs";
import { DialogService } from "../../../../shared/services/dialog.service";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";

@Component({
  selector: "app-add-classroom-page",
  templateUrl: "./add-classroom-page.component.html",
  styleUrls: ["./add-classroom-page.component.css"]
})
export class AddClassroomPageComponent implements OnInit {
  @ViewChild(AddClassroomFormComponent)
  addClassroomFormComponent: AddClassroomFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addClassroomFormComponent.classroomForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
