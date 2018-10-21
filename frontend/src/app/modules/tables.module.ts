import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { MaterialModule } from "./material.module";

import { ClassroomService } from "../services/classroom.service";
import { UserService } from "../services/user.service";
import { ReservationService } from "../services/reservation.service";
import { SubjectService } from "../services/subject.service";
import { BuildingService } from "../services/building.service";
import { SemesterService } from "../services/semester.service";
import { ClassReservationService } from "../services/class-reservation.service";
import { EventReservationService } from "../services/event-reservation.service";

import { ClassroomTableComponent } from "../components/tables/classroom-table/classroom-table.component";
import { UserTableComponent } from "../components/tables/user-table/user-table.component";
import { SubjectTableComponent } from "../components/tables/subject-table/subject-table.component";
import { BuildingTableComponent } from "../components/tables/building-table/building-table.component";
import { SemesterTableComponent } from "../components/tables/semester-table/semester-table.component";
import { ClassReservationTableComponent } from "../components/tables/class-reservation-table/class-reservation-table.component";
import { EventReservationTableComponent } from "../components/tables/event-reservation-table/event-reservation-table.component";
import { ReservationTableComponent } from "../components/tables/reservation-table/reservation-table.component";

import { MyReservationsComponent } from "../components/other/my-reservations/my-reservations.component";
import { ReservationsComponent } from "../components/other/reservations/reservations.component";
import { PendingReservationsComponent } from '../components/other/pending-reservations/pending-reservations.component';

import { EmptyPipe } from "../pipes/empty.pipe";
import { StatusConverterPipe } from "../pipes/statusConverter.pipe";

@NgModule({
  imports: [CommonModule, MaterialModule],
  declarations: [
    ClassroomTableComponent,
    UserTableComponent,
    SubjectTableComponent,
    BuildingTableComponent,
    SemesterTableComponent,
    ClassReservationTableComponent,
    EventReservationTableComponent,
    ReservationTableComponent,
    MyReservationsComponent,
    ReservationsComponent,
    PendingReservationsComponent,

    EmptyPipe,
    StatusConverterPipe
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
    EventReservationTableComponent,
    ReservationTableComponent,
    MyReservationsComponent,
    ReservationsComponent,
    PendingReservationsComponent,
  ] //Amit akarunk, hogy más komponens használhasson
})
export class TablesModule {}
