import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';

import { ClassroomService } from '../services/classroom.service';
import { UserService } from '../services/user.service';
import { ReservationService } from '../services/reservation.service';
import { SubjectService } from '../services/subject.service';
import { BuildingService } from '../services/building.service';
import { SemesterService } from '../services/semester.service';

import { ClassroomTableComponent } from '../components/tables/classroom-table/classroom-table.component';
import { UserTableComponent } from '../components/tables/user-table/user-table.component';
import { SubjectTableComponent } from '../components/tables/subject-table/subject-table.component';
import { BuildingTableComponent } from '../components/tables/building-table/building-table.component';
import { SemesterTableComponent } from '../components/tables/semester-table/semester-table.component';
import { ClassReservationTableComponent } from '../components/tables/class-reservation-table/class-reservation-table.component';
import { EventReservationTableComponent } from '../components/tables/event-reservation-table/event-reservation-table.component';
import { ClassReservationService } from '../services/class-reservation.service';
import { EventReservationService } from '../services/event-reservation.service';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule
  ],
  declarations: [
    ClassroomTableComponent,
    UserTableComponent,
    SubjectTableComponent,
    BuildingTableComponent,
    SemesterTableComponent,
    ClassReservationTableComponent,
    EventReservationTableComponent
  ], //Komponensek, pipe-ok
  providers: [
      ClassroomService,
      UserService,
      ClassReservationService,
      EventReservationService,
      SubjectService,
      BuildingService,
      SemesterService
  ], //Service(s)
  exports: [
    ClassroomTableComponent,
    UserTableComponent,
    SubjectTableComponent,
    BuildingTableComponent,
    SemesterTableComponent,
    ClassReservationTableComponent,
    EventReservationTableComponent
  ] //Amit akarunk, hogy más komponens használhasson
})
export class TablesModule {}