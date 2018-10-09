import { Component, OnInit } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { ValidatorService } from "../../../services/validator.service";
import { ClassroomService } from "../../../services/classroom.service";
import { Classroom } from "../../../models/Classroom";
import { SnackbarService } from "../../../services/snackbar.service";
import { MatDialog } from "@angular/material";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../services/dialog.service";
import { BuildingService } from "../../../services/building.service";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { MessageConstants } from "../../../config/message-constants.config";
import { Observable } from "rxjs";
import { TextUtils } from "../../../utils/text-utils";

@Component({
  selector: "app-add-classroom-form",
  templateUrl: "./add-classroom-form.component.html",
  styleUrls: ["./add-classroom-form.component.css"]
})
export class AddClassroomFormComponent implements OnInit {
  buildings: string[];

  building = new FormControl("", [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(30)
  ]);

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
    private classroomService: ClassroomService,
    private buildingService: BuildingService,
    private dialogService: DialogService
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
        res => console.log(res),
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
