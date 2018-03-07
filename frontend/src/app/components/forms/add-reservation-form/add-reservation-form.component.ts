import { Component, OnInit } from '@angular/core';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';
import { ValidatorService } from '../../../services/validator.service';
import { DialogService } from '../../../services/dialog.service';
import { AuthService } from '../../../authentication/auth.service';
import { BuildingService } from '../../../services/building.service';
import { Router } from '@angular/router';
import { Subject } from '../../../models/Subject';
import { Building } from '../../../models/Building';

@Component({
  selector: 'app-add-reservation-form',
  templateUrl: './add-reservation-form.component.html',
  styleUrls: ['./add-reservation-form.component.css']
})
export class AddReservationFormComponent implements OnInit {

  days: string[] = ['Hétfő', 'Kedd', 'Szerda', 'Csütörtök', 'Péntek', 'Szombat'];
  username: string;
  roomNames: string[];
  subjects: Subject[];
  buildings: Building[];

  room = new FormControl('', [
    Validators.required
  ]);

  building = new FormControl('', [
    Validators.required
  ]);

  subject = new FormControl('', [
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
    room: this.room,
    building: this.building,
    subject: this.subject,
    startDate: this.startDate,
    endDate: this.endDate,
    day: this.day,
    startTime: this.startTime,
    endTime: this.endTime
  });

  constructor(
    private authService: AuthService,
    private classroomService: ClassroomService,
    private subjectService: SubjectService,
    private reservationService: ReservationService,
    private buildingService: BuildingService,
    private builder: FormBuilder,
    private dialogService: DialogService,
    private validatorService: ValidatorService,
    private router: Router
  ) { }

  ngOnInit() {
    this.username = this.authService.getUsername();
    this.getSubjects();
    this.getBuildings();
  }

  getRoomNamesByBuilding(name: string) {
    this.classroomService.getRoomNamesByBuilding(name).subscribe(
      res => this.roomNames = res
    )
  }

  getBuildings() {
    this.buildingService.getAll().subscribe(
      res => this.buildings = res
    )
  }

  getSubjects() {
    this.subjectService.getAll().subscribe(
      res => this.subjects = res
    )
  }

  formToReservation(): Reservation {
    const subject_details = this.getSubjectDetails();

    return new Reservation(
      this.username, //Tesztelésig
      this.reservationForm.value.room,
      subject_details[1].trim(),
      subject_details[0].trim(),
      this.reservationForm.value.building,
      this.reservationForm.value.startDate,
      this.reservationForm.value.endDate,
      this.reservationForm.value.day,
      this.reservationForm.value.startTime,
      this.reservationForm.value.endTime,
    );
  }

  /*
    Split '|' karakter mentén
  */
  private getSubjectDetails(): string[] {
    return this.reservationForm.value.subject.split('|');
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addReservation() {
    this.reservationService.createRes(this.formToReservation()).subscribe(
      res => console.log(res),
      error => {
        this.dialogService.openDialog("Foglalás hozzáadása:", this.dialogService.addBr(error.error), InfoDialogComponent);
      },
      () => this.dialogService.openDialog("Foglalás hozzáadása:", "Foglalás sikeresen rögítve", InfoDialogComponent)
    );
  }
}
