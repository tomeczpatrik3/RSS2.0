import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';

import { ClassroomService } from '../services/classroom.service';
import { UserService } from '../services/user.service';
import { ReservationService } from '../services/reservation.service';
import { SubjectService } from '../services/subject.service';
import { BuildingService } from '../services/building.service';
import { TicketService } from '../services/ticket.service';

import { ClassroomTableComponent } from '../components/tables/classroom-table/classroom-table.component';
import { UserTableComponent } from '../components/tables/user-table/user-table.component';
import { ReservationTableComponent } from '../components/tables/reservation-table/reservation-table.component';
import { MyReservationsTableComponent } from '../components/tables/my-reservations-table/my-reservations-table.component';
import { SubjectTableComponent } from '../components/tables/subject-table/subject-table.component';
import { BuildingTableComponent } from '../components/tables/building-table/building-table.component';
import { TicketTableComponent } from '../components/tables/ticket-table/ticket-table.component';
import { DetailedReservationsComponent } from '../components/detailed-reservations/detailed-reservations.component';


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
    TicketTableComponent
  ], //Komponensek, pipe-ok
  providers: [
      ClassroomService,
      UserService,
      ReservationService,
      SubjectService,
      BuildingService,
      TicketService
  ], //Service(s)
  exports: [
    ClassroomTableComponent,
    UserTableComponent,
    ReservationTableComponent,
    MyReservationsTableComponent,
    SubjectTableComponent,
    BuildingTableComponent,
    DetailedReservationsComponent,
    TicketTableComponent
  ] //Amit akarunk, hogy más komponens használhasson
})
export class TablesModule {}