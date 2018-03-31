import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';

import { ClassroomService } from '../services/classroom.service';
import { UserService } from '../services/user.service';
import { ReservationService } from '../services/reservation.service';
import { SubjectService } from '../services/subject.service';
import { BuildingService } from '../services/building.service';
import { SemesterService } from '../services/semester.service';

import { ClassroomTableComponent } from '../components/classroom/classroom-table/classroom-table.component';
import { UserTableComponent } from '../components/user/user-table/user-table.component';
import { ReservationTableComponent } from '../components/reservation/reservation-table/reservation-table.component';
import { MyReservationsTableComponent } from '../components/reservation/my-reservations-table/my-reservations-table.component';
import { SubjectTableComponent } from '../components/subject/subject-table/subject-table.component';
import { BuildingTableComponent } from '../components/building/building-table/building-table.component';
import { DetailedReservationsComponent } from '../components/reservation/detailed-reservations/detailed-reservations.component';
import { PendingReservationsComponent } from '../components/reservation/pending-reservations/pending-reservations.component';
import { PendingReservationsTableComponent } from '../components/reservation/pending-reservations-table/pending-reservations-table.component';
import { SemesterTableComponent } from '../components/semester/semester-table/semester-table.component';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule
  ],
  declarations: [
    ClassroomTableComponent,
    UserTableComponent,
    ReservationTableComponent,
    MyReservationsTableComponent,
    SubjectTableComponent,
    BuildingTableComponent,
    DetailedReservationsComponent,
    PendingReservationsComponent,
    PendingReservationsTableComponent,
    SemesterTableComponent
  ], //Komponensek, pipe-ok
  providers: [
      ClassroomService,
      UserService,
      ReservationService,
      SubjectService,
      BuildingService,
      SemesterService
  ], //Service(s)
  exports: [
    ClassroomTableComponent,
    UserTableComponent,
    ReservationTableComponent,
    MyReservationsTableComponent,
    SubjectTableComponent,
    BuildingTableComponent,
    DetailedReservationsComponent,
    PendingReservationsComponent,
    PendingReservationsTableComponent,
    SemesterTableComponent
  ] //Amit akarunk, hogy más komponens használhasson
})
export class TablesModule {}