import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder
} from "@angular/forms";
import { DialogService } from "../../../services/dialog.service";
import { SemesterService } from "../../../services/semester.service";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { Semester } from "../../../models/Semester";
import { Observable } from "rxjs";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { MessageConstants } from "../../../config/message-constants.config";
import { TextUtils } from "../../../utils/text-utils";

@Component({
  selector: "app-add-semester-form",
  templateUrl: "./add-semester-form.component.html",
  styleUrls: ["./add-semester-form.component.css"]
})
export class AddSemesterFormComponent implements OnInit {
  startDate = new FormControl("", [Validators.required]);

  endDate = new FormControl("", [Validators.required]);

  semesterName = new FormControl("", [
    Validators.required,
    Validators.minLength(11),
    Validators.maxLength(11)
  ]);

  semesterForm: FormGroup = this.builder.group({
    semesterName: this.semesterName,
    startDate: this.startDate,
    endDate: this.endDate
  });

  constructor(
    private builder: FormBuilder,
    private dialogService: DialogService,
    private semesterService: SemesterService
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
      res => console.log(res),
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
