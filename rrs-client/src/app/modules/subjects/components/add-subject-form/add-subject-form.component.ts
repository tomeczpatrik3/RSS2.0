import { Component, OnInit } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { SubjectsDataService } from "../../subjects.data.service";
import { Subject } from "../../../../shared/models/Subject";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { UniqueSubjectCodeValidator } from "../../../../shared/directives/unique-subject-code.directive";

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

  subjectCode = new FormControl("", {
    validators: [
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(15)
    ],
    asyncValidators: this.subjectValidator.validate.bind(this.subjectValidator),
    updateOn: "blur"
  });

  subjectForm: FormGroup = this.builder.group({
    subjectName: this.subjectName,
    subjectCode: this.subjectCode
  });

  constructor(
    private builder: FormBuilder,
    private subjectService: SubjectsDataService,
    private dialogService: DialogService,
    private subjectValidator: UniqueSubjectCodeValidator
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
