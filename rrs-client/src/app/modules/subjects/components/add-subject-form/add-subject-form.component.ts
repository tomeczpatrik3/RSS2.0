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
import { TextUtils } from "../../../../shared/utils/text-utils";
import { UniqueSubjectCodeValidator } from "../../../../shared/directives/unique-subject-code.directive";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-add-subject-form",
  templateUrl: "./add-subject-form.component.html",
  styleUrls: ["./add-subject-form.component.css"]
})
export class AddSubjectFormComponent implements OnInit {
  /*A tárgy neve*/
  subjectName = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  /*A tárgykód*/
  subjectCode = new FormControl("", {
    validators: [
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(15)
    ],
    asyncValidators: this.subjectValidator.validate.bind(this.subjectValidator),
    updateOn: "blur"
  });

  /*Az űrlap*/
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

  /**
   * Az űrlap tantárggyá konvertálását megvalósító függvény
   */
  formToSubject(): Subject {
    return new Subject(
      this.subjectForm.value.subjectName,
      this.subjectForm.value.subjectCode
    );
  }

  /**
   * A tantárgy létrehozását megvalósító függvény
   */
  addSubject() {
    this.subjectService
      .createSubject(this.formToSubject())
      .subscribe(
        () => {},
        error =>
          this.dialogService.openDialog(
            "Tantárgy hozzáadása:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
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
}
