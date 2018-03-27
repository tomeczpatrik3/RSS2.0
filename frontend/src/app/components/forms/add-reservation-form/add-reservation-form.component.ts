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
import { ReservationForm } from '../../../models/ReservationForm';
import { SemesterService } from '../../../services/semester.service';

@Component({
  selector: 'app-add-reservation-form',
  templateUrl: './add-reservation-form.component.html',
  styleUrls: ['./add-reservation-form.component.css']
})
export class AddReservationFormComponent implements OnInit {


  days: string[] = ['Hétfő', 'Kedd', 'Szerda', 'Csütörtök', 'Péntek', 'Szombat', 'Vasárnap'];
  roomNames: string[];
  subjects: Subject[];
  buildings: Building[];
  semesterNames: string[];

  /**
   * A foglalás típusának beállításához szükséges adattagok:
   */
  reservationType: string = "";
  reservationTypes: string[] = [ "Foglalás egy adott napra", "Foglalás az egész szemeszterre"];

  /**
   * Az egyes formcontrollok:
   */
  semester = new FormControl('', [
    Validators.required
  ]);

  subject = new FormControl('', [
    Validators.required
  ]);

  room = new FormControl('', [
    Validators.required
  ]);

  building = new FormControl('', [
    Validators.required
  ]);

  type = new FormControl('', [
    Validators.required
  ]);

  date = new FormControl('', [
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

  note = new FormControl('', []);

  //-------------------------------

  /**
   * Formgroup felépítése a formcontrollokból:
   */
  reservationForm: FormGroup = this.builder.group({
    semester: this.semester,
    room: this.room,
    building: this.building,
    subject: this.subject,
    date: this.date,
    day: this.day,
    startTime: this.startTime,
    endTime: this.endTime,
    note: this.note
  });

  /**
   * Service-k inicializálása:
   * @param authService 
   * @param classroomService 
   * @param subjectService 
   * @param reservationService 
   * @param buildingService 
   * @param builder 
   * @param dialogService 
   * @param validatorService 
   * @param router 
   */
  constructor(
    private authService: AuthService,
    private classroomService: ClassroomService,
    private subjectService: SubjectService,
    private reservationService: ReservationService,
    private buildingService: BuildingService,
    private semesterService: SemesterService,
    private builder: FormBuilder,
    private dialogService: DialogService,
    private validatorService: ValidatorService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getSubjects();
    this.getBuildings();
  }

  /**
   * Tantermek neveinek lekérdezése az épület alapján:
   * @param name Az épület neve
   */
  getRoomNamesByBuilding(name: string) {
    this.classroomService.getRoomNamesByBuilding(name).subscribe(
      res => this.roomNames = res
    )
  }

  /**
   * Épületek lekérdezése:
   */
  getBuildings() {
    this.buildingService.getAll().subscribe(
      res => this.buildings = res
    )
  }

  /**
   * Tantárgyak lekérdezése:
   */
  getSubjects() {
    this.subjectService.getAll().subscribe(
      res => this.subjects = res
    )
  }

  /**
   * A félévek lekérdezése:
   */
  getSemesterNames() {
    this.semesterService.getSemesterNames().subscribe(
      res => this.semesterNames = res
    )
  }

  /**
   * Az űrlap foglalássá konvertálása:
   */
  formToReservation(): ReservationForm {
    const subject_details = this.getSubjectDetails();

    return new ReservationForm(
      this.authService.getUsername(),
      this.reservationForm.value.semester,
      subject_details[0].trim(),
      this.reservationForm.value.building,
      this.reservationForm.value.room,
      this.reservationForm.value.note,
      ["ASD"] //TODO GENERATE DATES!!
    );
  }

  /**
   * A tantárgy nevének és kódjának szétválasztására használt függvény
   * A split a '|' karakter mentén történik
   */
  private getSubjectDetails(): string[] {
    return this.reservationForm.value.subject.split('|');
  }

  /**
   * Feliratkozunk, majd:
   * - hiba esetén jelzünk a hibát dialog segítségével
   * - siker esetén jelezzük a sikert dialog segítségével
   */
  addReservation() {
    this.reservationService.createRes(this.formToReservation()).subscribe(
      res => console.log(res),
      error => {
        this.dialogService.openDialog("Foglalás hozzáadása:", this.dialogService.addBr(error.error), InfoDialogComponent);
      },
      () => this.dialogService.openDialog("Foglalás hozzáadása:", "Foglalás rögítve, elbírálás alá került!", InfoDialogComponent)
    );
  }

  /**
   * A foglalás típusának beállítása:
   * d --> day
   * s --> semester
   * @param type A foglalás típusa (amit kiválasztunk a legördülő listából)
   */
  setReservationType(type: string) {
    switch(type) {
      case "Foglalás egy adott napra": this.reservationType = "d"; break;
      case "Foglalás az egész szemeszterre": this.reservationType = "s"; break;
    }
  }

  /**
   * Dátumhoz tartozó nap lekérdezése:
   */
  getDayOfWeek(date) {
    var dayOfWeek = new Date(date).getDay();
    return isNaN(dayOfWeek) ? null : ['Vasárnap', 'Hétfő', 'Kedd', 'Szerda', 'Csütörtök', 'Péntek', 'Szombat'][dayOfWeek];
  }
}
