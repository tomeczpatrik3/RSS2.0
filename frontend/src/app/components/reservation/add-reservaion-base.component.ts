import { Injectable, OnInit } from "@angular/core";
import { Subject } from "../../models/Subject";
import { Building } from "../../models/Building";
import { Semester } from "../../models/Semester";
import { AuthService } from "../../authentication/auth.service";
import { ClassroomService } from "../../services/classroom.service";
import { SubjectService } from "../../services/subject.service";
import { ReservationService } from "../../services/reservation.service";
import { BuildingService } from "../../services/building.service";
import { SemesterService } from "../../services/semester.service";
import { FormBuilder } from "@angular/forms";
import { DialogService } from "../../services/dialog.service";
import { ValidatorService } from "../../services/validator.service";
import { Router } from "@angular/router";
import { Classroom } from "../../models/Classroom";
import { InfoDialogComponent } from "../dialogs/info-dialog/info-dialog.component";

@Injectable()
export abstract class AddReservationBaseComponent implements OnInit {
    rooms:      Classroom[];
    subjects:   Subject[];
    buildings:  Building[];
    semesters:  Semester[];  
  
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
    protected authService:        AuthService,
    protected classroomService:   ClassroomService,
    protected subjectService:     SubjectService,
    protected reservationService: ReservationService,
    protected buildingService:    BuildingService,
    protected semesterService:    SemesterService,
    protected builder:            FormBuilder,
    protected dialogService:      DialogService,
    protected validatorService:   ValidatorService,
    protected router:             Router
  ) { }

  ngOnInit() {
    this.getSubjects();
    this.getBuildings();
    this.getSemesters();
  }

  /**
   * Tantermek neveinek lekérdezése az épület alapján:
   * @param name Az épület neve
   */
  protected getRoomsByBuildingName(buildingName: string) {
    this.classroomService.findByBuildingName(buildingName).subscribe(
      res => this.rooms = res
    )
  }

  /**
   * Épületek lekérdezése:
   */
  protected getBuildings() {
    this.buildingService.getAll().subscribe(
      res => this.buildings = res
    )
  }

  /**
   * Tantárgyak lekérdezése:
   */
  protected getSubjects() {
    this.subjectService.getAll().subscribe(
      res => this.subjects = res
    )
  }

  /**
   * A félévek lekérdezése:
   */
  protected getSemesters() {
    this.semesterService.getAll().subscribe(
      res => this.semesters = res
    )
  }

  /**
   * A tantárgy nevének és kódjának szétválasztására használt függvény
   * A split a '|' karakter mentén történik
   */
  protected getSubjectCode(subject: string): string {
    return subject.split('|')[1];
  }

  /**
   * Az űrlap foglalássá konvertálása:
   */
  protected abstract formToReservation();

  /**
   * Foglalás elkészítése:
   */
  protected abstract addReservation();

  /**
   * Dátumhoz tartozó nap lekérdezése:
   */
  protected getDayOfWeek(date) {
    var dayOfWeek = new Date(date).getDay();
    return isNaN(dayOfWeek) ? null : ['Vasárnap', 'Hétfő', 'Kedd', 'Szerda', 'Csütörtök', 'Péntek', 'Szombat'][dayOfWeek];
  }
}