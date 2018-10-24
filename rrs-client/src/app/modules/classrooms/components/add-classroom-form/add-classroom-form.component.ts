import { Component, OnInit } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { ValidatorService } from "../../../../shared/services/validator.service";
import { ClassroomsDataService } from "../../classrooms.data.service";
import { Classroom } from "../../../../shared/models/Classroom";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../../shared/services/dialog.service";
import { BuildingApiService } from "../../../../shared/services/api/building.api.service";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { Observable } from "rxjs";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { TakenBuildingNameValidator } from "../../../../shared/directives/taken-building-name.directive";

@Component({
  selector: "app-add-classroom-form",
  templateUrl: "./add-classroom-form.component.html",
  styleUrls: ["./add-classroom-form.component.css"]
})
export class AddClassroomFormComponent implements OnInit {
  buildings: string[];

  building = new FormControl("", {
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

  roomName = new FormControl("", [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(30)
  ]);

  chairs = new FormControl("", [
    Validators.required,
    this.validatorService.isInteger
  ]);

  pc = new FormControl(false);

  projector = new FormControl(false);

  classroomForm: FormGroup = this.builder.group({
    building: this.building,
    roomName: this.roomName,
    chairs: this.chairs,
    pc: this.pc,
    projector: this.projector
  });

  //-----
  letThrough: boolean;

  constructor(
    private builder: FormBuilder,
    private validatorService: ValidatorService,
    private classroomService: ClassroomsDataService,
    private buildingService: BuildingApiService,
    private dialogService: DialogService,
    private buildingValidator: TakenBuildingNameValidator
  ) {}

  ngOnInit() {
    this.loadBuildings();
  }

  loadBuildings() {
    this.buildingService.getNames().subscribe(res => (this.buildings = res));
  }

  formToClassroom(): Classroom {
    return new Classroom(
      this.classroomForm.value.roomName,
      this.classroomForm.value.pc,
      this.classroomForm.value.projector,
      this.classroomForm.value.chairs,
      this.classroomForm.value.building
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addClassroom() {
    this.classroomService
      .createClassroom(this.formToClassroom())
      .subscribe(
        res => {},
        error =>
          this.dialogService.openDialog(
            "Tanterem hozzáadása:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
          ),
        () =>
          this.dialogService.openDialog(
            "Tanterem hozzáadása:",
            "Tanterem sikeresen rögítve",
            InfoDialogComponent
          )
      );
    this.classroomForm.reset();
    this.loadBuildings();
  }

  canDeactivate(): Observable<boolean> | boolean {
    if (this.classroomForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
