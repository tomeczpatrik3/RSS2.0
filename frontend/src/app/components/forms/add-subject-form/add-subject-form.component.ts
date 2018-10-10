import { Component, OnInit } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { SnackbarService } from "../../../services/snackbar.service";
import { SubjectService } from "../../../services/subject.service";
import { Subject } from "../../../models/Subject";
import { MatDialog } from "@angular/material";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../config/message-constants.config";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { TextUtils } from "../../../utils/text-utils";

@Component({
  selector: "app-add-subject-form",
  templateUrl: "./add-subject-form.component.html",
  styleUrls: ["./add-subject-form.component.css"]
})
export class AddSubjectFormComponent implements OnInit {
  subjectName = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  subjectCode = new FormControl("", [
    Validators.required,
    Validators.minLength(4),
    Validators.maxLength(15)
  ]);

  subjectForm: FormGroup = this.builder.group({
    subjectName: this.subjectName,
    subjectCode: this.subjectCode
  });

  constructor(
    private builder: FormBuilder,
    private subjectService: SubjectService,
    private dialogService: DialogService
  ) {}

  ngOnInit() {}

  formToSubject(): Subject {
    return new Subject(
      this.subjectForm.value.subjectName,
      this.subjectForm.value.subjectCode
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addSubject() {
    this.subjectService
      .createSubject(this.formToSubject())
      .subscribe(
        res => {},
        error =>
          this.dialogService.openDialog(
            "Tantárgy hozzáadása:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
          ),
        () =>
          this.dialogService.openDialog(
            "Tantárgy hozzáadása:",
            "Tantárgy sikeresen rögítve",
            InfoDialogComponent
          )
      );
    this.subjectForm.reset();
  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.subjectForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
