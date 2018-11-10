import { Injectable, OnInit } from "@angular/core";
import { Subject } from "../../../shared/models/Subject";
import { Building } from "../../../shared/models/Building";
import { Semester } from "../../../shared/models/Semester";
import { AuthService } from "../../../shared/services/auth.service";
import { ClassroomApiService } from "../../../shared/services/api/classroom.api.service";
import { SubjectApiService } from "../../../shared/services/api/subject.api.service";
import { BuildingApiService } from "../../../shared/services/api/building.api.service";
import { SemesterApiService } from "../../../shared/services/api/semester.api.service";
import { FormBuilder } from "@angular/forms";
import { DialogService } from "../../../shared/services/dialog.service";
import { ValidatorService } from "../../../shared/services/validator.service";
import { Router } from "@angular/router";
import { Classroom } from "../../../shared/models/Classroom";
import { Day } from "../../../shared/enums/Day";

@Injectable()
export abstract class AddReservation implements OnInit {
  rooms: Classroom[];
  subjects: Subject[];
  buildings: Building[];
  semesters: Semester[];

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
    protected authService: AuthService,
    protected classroomService: ClassroomApiService,
    protected subjectService: SubjectApiService,
    protected buildingService: BuildingApiService,
    protected semesterService: SemesterApiService,
    protected builder: FormBuilder,
    protected dialogService: DialogService,
    protected validatorService: ValidatorService,
    protected router: Router
  ) {
    this.getSubjects();
    this.getBuildings();
    this.getOpenedSemesters();
  }

  ngOnInit() {}

  /**
   * Tantermek neveinek lekérdezése az épület alapján:
   * @param name Az épület neve
   */
  getRoomsByBuildingName(buildingName: string) {
    this.classroomService
      .findByBuildingName(buildingName)
      .subscribe(res => (this.rooms = res));
  }

  /**
   * Épületek lekérdezése:
   */
  getBuildings() {
    this.buildingService.getAll().subscribe(res => (this.buildings = res));
  }

  /**
   * Tantárgyak lekérdezése:
   */
  getSubjects() {
    this.subjectService.getAll().subscribe(res => (this.subjects = res));
  }

  /**
   * A félévek lekérdezése:
   */
  getOpenedSemesters() {
    this.semesterService.getOpened().subscribe(res => (this.semesters = res));
  }

  /**
   * A tantárgy nevének és kódjának szétválasztására használt függvény
   * A split a '|' karakter mentén történik
   */
  getSubjectCode(subject: string): string {
    return subject.split("|")[0].trim();
  }

  /**
   * Az űrlap foglalássá konvertálása:
   */
  abstract formToReservation();

  /**
   * Foglalás elkészítése:
   */
  abstract addReservation();

  /**
   * Dátumhoz tartozó nap lekérdezése:
   */
  getDayOfWeek(date) {
    var dayOfWeek = new Date(date).getDay();
    return isNaN(dayOfWeek) ? null : Day[dayOfWeek];
  }
}
