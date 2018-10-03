import { Component, OnInit } from '@angular/core';
import { AddReservation } from '../add-reservaion';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';

import { AuthService } from '../../../authentication/auth.service';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { ClassReservationService } from '../../../services/class-reservation.service';
import { BuildingService } from '../../../services/building.service';
import { SemesterService } from '../../../services/semester.service';
import { DialogService } from '../../../services/dialog.service';
import { ValidatorService } from '../../../services/validator.service';
import { Router } from '@angular/router';
import { ClassReservation } from '../../../models/ClassReservation';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';


@Component({
  selector: 'app-add-simple-reservation',
  templateUrl: './add-simple-reservation.component.html',
  styleUrls: ['./add-simple-reservation.component.css']
})
export class AddSimpleReservationComponent extends AddReservation {
  /**
   * Az egyes formcontrollok:
   */
  semester = new FormControl('', [
    Validators.required
  ]);

  subject = new FormControl('', [
    Validators.required
  ]);

  building = new FormControl('', [
    Validators.required
  ]);

  room = new FormControl('', [
    Validators.required
  ]);

  date = new FormControl('', [
    Validators.required
  ]);

  startTime = new FormControl('', [
    Validators.required,
    this.validatorService.isTime,
    Validators.minLength(5),
    Validators.maxLength(5)
  ]);

  endTime = new FormControl('', [
    Validators.required,
    this.validatorService.isTime,
    Validators.minLength(5),
    Validators.maxLength(5)
  ]);

  note = new FormControl('', []);

  //-------------------------------

  /**
   * Formgroup felépítése a formcontrollokból:
   */
  reservationForm: FormGroup = this.builder.group({
    semester: this.semester,
    subject: this.subject,
    building: this.building,
    room: this.room,
    date: this.date,
    startTime: this.startTime,
    endTime: this.endTime,
    note: this.note
  });

  constructor(
    protected authService:        AuthService,
    protected classroomService:   ClassroomService,
    protected subjectService:     SubjectService,
    protected classReservationService: ClassReservationService,
    protected buildingService:    BuildingService,
    protected semesterService:    SemesterService,
    protected builder:            FormBuilder,
    protected dialogService:      DialogService,
    protected validatorService:   ValidatorService,
    protected router:             Router
  ) { 
    super(
      authService,
      classroomService,
      subjectService,
      buildingService,
      semesterService,
      builder,
      dialogService,
      validatorService,
      router
    );
  }

  protected formToReservation() {
    return ClassReservation.buildSimple(
      this.authService.getUsername(),
      this.building.value,
      this.room.value,
      this.note.value,
      this.semester.value,
      this.getSubjectCode(this.subject.value),
      this.date.value,
      this.startTime.value,
      this.endTime.value
    )
  }

  /**
   * Feliratkozunk, majd:
   * - hiba esetén jelzünk a hibát dialog segítségével
   * - siker esetén jelezzük a sikert dialog segítségével
   */
  protected addReservation() {
    this.classReservationService.createClassReservation(this.formToReservation()).subscribe(
      res => console.log(res),
      error => {
        this.dialogService.openDialog("Foglalás hozzáadása:", this.dialogService.addBr(error.error), InfoDialogComponent);
      },
      () => this.dialogService.openDialog("Foglalás hozzáadása:", "Foglalás rögítve, elbírálás alá került!", InfoDialogComponent)
    );    
  }
}
