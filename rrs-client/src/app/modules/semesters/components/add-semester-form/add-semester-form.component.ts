import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";
import { DialogService } from "../../../../shared/services/dialog.service";
import { SemesterDataService } from "../../semesters.data.service";
import { UniqueSemesterNameValidator } from "../../../../shared/directives/unique-semester-name.directive";
import { Semester } from "../../../../shared/models/Semester";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";
import { Observable } from "rxjs";

@Component({
  selector: "app-add-semester-form",
  templateUrl: "./add-semester-form.component.html",
  styleUrls: ["./add-semester-form.component.css"]
})
export class AddSemesterFormComponent implements OnInit {
  startDate = new FormControl("", [Validators.required]);

  endDate = new FormControl("", [Validators.required]);

  semesterName = new FormControl("", {
    validators: [
      Validators.required,
      Validators.minLength(11),
      Validators.maxLength(11)
    ],
    asyncValidators: this.semesterValidator.validate.bind(
      this.semesterValidator
    ),
    updateOn: "blur"
  });

  semesterForm: FormGroup = this.builder.group({
    semesterName: this.semesterName,
    startDate: this.startDate,
    endDate: this.endDate
  });

  constructor(
    private builder: FormBuilder,
    private dialogService: DialogService,
    private semesterService: SemesterDataService,
    private semesterValidator: UniqueSemesterNameValidator
  ) {}

  ngOnInit() {}

  formToSemester(): Semester {
    return new Semester(
      this.semesterForm.value.semesterName,
      this.semesterForm.value.startDate,
      this.semesterForm.value.endDate
    );
  }

  addSemester() {
    this.semesterService.createSemester(this.formToSemester()).subscribe(
      res => {},
      error => {
        this.dialogService.openDialog(
          "Szemeszter hozzáadása:",
          TextUtils.addBreaks(error.error),
          InfoDialogComponent
        );
      },
      () =>
        this.dialogService.openDialog(
          "Szemeszter hozzáadása:",
          "Szemeszter rögítve!",
          InfoDialogComponent
        )
    );

    this.semesterForm.reset();
  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.semesterForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
