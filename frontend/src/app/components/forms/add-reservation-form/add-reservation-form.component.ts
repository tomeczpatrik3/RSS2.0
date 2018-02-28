import { Component, OnInit } from '@angular/core';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';
import { ValidatorService } from '../../../services/validator.service';

@Component({
  selector: 'app-add-reservation-form',
  templateUrl: './add-reservation-form.component.html',
  styleUrls: ['./add-reservation-form.component.css']
})
export class AddReservationFormComponent implements OnInit {

  username = "fannika123";

  days: string[] = ['Hétfő', 'Kedd', 'Szerda', 'Csütörtök', 'Péntek', 'Szombat'];

  roomNames: string[];
  subjectNames: string[];

  roomName = new FormControl('', [
    Validators.required
  ]);

  subjectName = new FormControl('', [
    Validators.required
  ]);

  startDate = new FormControl('', [
    Validators.required
  ]);

  endDate = new FormControl('', [
    Validators.required
  ]);

  day = new FormControl('', [
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

  reservationForm: FormGroup = this.builder.group({
    roomName: this.roomName,
    subjectName: this.subjectName,
    startDate: this.startDate,
    endDate: this.endDate,
    day: this.day,
    startTime: this.startTime,
    endTime: this.endTime
  });

  constructor(
    private classroomService: ClassroomService,
    private subjectService: SubjectService,
    private reservationService: ReservationService,
    private builder: FormBuilder,
    private dialog: MatDialog,
    private validatorService: ValidatorService,
  ) { }

  ngOnInit() {
    this.getClassroomNames();
    this.getSubjectNames();
  }

  getClassroomNames() {
    this.classroomService.getRoomNames().subscribe(
      res => this.roomNames = res
    )
  }

  getSubjectNames() {
    this.subjectService.getSubjectNames().subscribe(
      res => this.subjectNames = res
    )
  }

  formToReservation(): Reservation {
    return new Reservation(
      this.username, //Tesztelésig
      this.reservationForm.value.roomName,
      this.reservationForm.value.subjectName,
      this.reservationForm.value.startDate,
      this.reservationForm.value.endDate,
      this.reservationForm.value.day,
      this.reservationForm.value.startTime,
      this.reservationForm.value.endTime,
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addReservation() {
    this.reservationService.createRes(this.formToReservation()).subscribe(
      res => console.log(res),
      error => this.openDialog("Foglalás hozzáadása:", "Hiba történt"),
      () => this.openDialog("Foglalás hozzáadása:", "Foglalás sikeresen rögítve")
    );
    
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
      this.reservationForm.reset();
    })
  }
}
