import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { ValidatorService } from '../../../services/validator.service';
import { ClassroomService } from '../../../services/classroom.service';
import { Classroom } from '../../../models/Classroom';
import { SnackbarService } from '../../../services/snackbar.service';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-add-classroom-form',
  templateUrl: './add-classroom-form.component.html',
  styleUrls: ['./add-classroom-form.component.css']
})
export class AddClassroomFormComponent implements OnInit {

  buildings: string[];

  building = new FormControl('', [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(15)
  ]);

  roomName = new FormControl('', [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(30)
  ]);

  chairs = new FormControl('', [
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

  constructor(
    private builder: FormBuilder,
    private validatorService: ValidatorService,
    private classroomService: ClassroomService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.loadBuildings();
  }

  loadBuildings() {
    this.classroomService.getBuildings().subscribe(
      res => this.buildings = res
    );
  }

  formToClassroom(): Classroom {
    return new Classroom(
      this.classroomForm.value.building,
      this.classroomForm.value.roomName,
      this.classroomForm.value.pc,
      this.classroomForm.value.projector,
      this.classroomForm.value.chairs
    )
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addClassroom() {
    this.classroomService.createClassroom(this.formToClassroom()).subscribe(
      res => console.log(res),
      error => this.openDialog("Tanterem hozzáadása:", "Hiba történt"),
      () => this.openDialog("Tanterem hozzáadása:", "Tanterem sikeresen rögítve")
    );
    this.loadBuildings();
  }

  /*
    Dialog megjelenítése, valamint adatok átadása:
    title: a dialog címe
    text: a dialogban közölt üzenet

    Dialog bezárása után reseteljük a formot!
  */
  openDialog(title_: string, text_: string) {
    let dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '600px',
      data: {
        title: title_,
        text: text_
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.classroomForm.reset();
    })
  }

}
