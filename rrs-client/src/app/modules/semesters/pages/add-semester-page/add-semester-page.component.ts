import { Component, OnInit, ViewChild } from '@angular/core';
import { AddSemesterFormComponent } from '../../components/add-semester-form/add-semester-form.component';
import { DialogService } from '../../../../shared/services/dialog.service';
import { Observable } from 'rxjs';
import { MessageConstants } from '../../../../shared/config/message-constants.config';
import { QuestionDialogComponent } from '../../../../shared/components/dialogs/question-dialog/question-dialog.component';

@Component({
  selector: 'app-add-semester-page',
  templateUrl: './add-semester-page.component.html',
  styleUrls: ['./add-semester-page.component.css']
})
export class AddSemesterPageComponent implements OnInit {
  @ViewChild(AddSemesterFormComponent)
  addSemesterFormComponent: AddSemesterFormComponent;

  constructor(private dialogService: DialogService) {}

  ngOnInit() {}

  /**
   * Az oldal elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.addSemesterFormComponent.semesterForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
