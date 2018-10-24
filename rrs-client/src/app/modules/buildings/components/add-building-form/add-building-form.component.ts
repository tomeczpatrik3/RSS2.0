import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  FormGroup,
  Validators,
  FormBuilder
} from "@angular/forms";
import { BuildingsDataService } from "../../buildings.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Building } from "../../../../shared/models/Building";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { Observable } from "rxjs/Observable";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { UniqueBuildingNameValidator } from "../../../../shared/directives/unique-building-name.directive";

@Component({
  selector: "app-add-building-form",
  templateUrl: "./add-building-form.component.html",
  styleUrls: ["./add-building-form.component.css"]
})
export class AddBuildingFormComponent implements OnInit {
  buildingName = new FormControl("", {
    validators: [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(30)
    ],
    asyncValidators: this.buildingValidator.validate.bind(
      this.buildingValidator
    ),
    updateOn: "blur"
  });

  buildingForm: FormGroup = this.builder.group({
    buildingName: this.buildingName
  });

  constructor(
    private builder: FormBuilder,
    private buildingService: BuildingsDataService,
    private dialogService: DialogService,
    private buildingValidator: UniqueBuildingNameValidator
  ) {}

  ngOnInit() {}

  formToSubject(): Building {
    return new Building(this.buildingForm.value.buildingName);
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addBuilding() {
    this.buildingService
      .createBuilding(this.formToSubject())
      .subscribe(
        res => {},
        error =>
          this.dialogService.openDialog(
            "Épület hozzáadása:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
          ),
        () =>
          this.dialogService.openDialog(
            "Épület hozzáadása:",
            "Épület sikeresen rögítve",
            InfoDialogComponent
          )
      );
    this.buildingForm.reset();
  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.buildingForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
