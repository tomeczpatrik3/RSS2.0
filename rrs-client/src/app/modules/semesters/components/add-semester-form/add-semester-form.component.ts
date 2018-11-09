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
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-add-semester-form",
  templateUrl: "./add-semester-form.component.html",
  styleUrls: ["./add-semester-form.component.css"]
})
export class AddSemesterFormComponent implements OnInit {
  /*A szemeszter kezdete*/
  startDate = new FormControl("", [Validators.required]);
  /*A szemeszter vége*/
  endDate = new FormControl("", [Validators.required]);
  /*A szemeszter "neve"*/
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
  /*Az űrlap*/
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

  /**
   * A űrlap szemeszterré konvertálását megvalósító függvény
   */
  formToSemester(): Semester {
    return new Semester(
      this.semesterForm.value.semesterName,
      this.semesterForm.value.startDate,
      this.semesterForm.value.endDate
    );
  }

  /**
   * A szemeszter létrehozását megvalósító függvény
   */
  addSemester() {
    this.semesterService.createSemester(this.formToSemester()).subscribe(
      () => {},
      error => {
        this.dialogService.openDialog(
          "Szemeszter hozzáadása:",
          TextUtils.addBreaks(error.error),
          ErrorDialogComponent
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
}
