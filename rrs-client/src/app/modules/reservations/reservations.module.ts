import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReservationsRoutingModule } from "./reservations-routing.module";
import { AddEventReservationFormComponent } from "./components/add-event-reservation-form/add-event-reservation-form.component";
import { AddSemesterFormComponent } from "../semesters/components/add-semester-form/add-semester-form.component";
import { AddSimpleReservationFormComponent } from "./components/add-simple-reservation-form/add-simple-reservation-form.component";
import { ClassReservationTableComponent } from "./components/class-reservation-table/class-reservation-table.component";
import { EventReservationTableComponent } from "./components/event-reservation-table/event-reservation-table.component";
import { EditClassReservationFormComponent } from "./components/edit-class-reservation-form/edit-class-reservation-form.component";
import { EditEventReservationFormComponent } from "./components/edit-event-reservation-form/edit-event-reservation-form.component";
import { ObserveClassReservationFormComponent } from "./components/observe-class-reservation-form/observe-class-reservation-form.component";
import { ObserveEventReservationFormComponent } from "./components/observe-event-reservation-form/observe-event-reservation-form.component";
import { FilterReservationFormComponent } from "./components/filter-reservation-form/filter-reservation-form.component";
import { ReservationTableComponent } from "./components/reservation-table/reservation-table.component";
import { MyReservationsComponent } from "./pages/my-reservations/my-reservations.component";
import { PendingReservationsComponent } from "./pages/pending-reservations/pending-reservations.component";
import { ReservationsComponent } from "./pages/reservations/reservations.component";

@NgModule({
  imports: [CommonModule, ReservationsRoutingModule],
  declarations: [
    AddEventReservationFormComponent,
    AddSemesterFormComponent,
    AddSimpleReservationFormComponent,
    ClassReservationTableComponent,
    EventReservationTableComponent,
    EditClassReservationFormComponent,
    EditEventReservationFormComponent,
    ObserveClassReservationFormComponent,
    ObserveEventReservationFormComponent,
    FilterReservationFormComponent,

    ReservationTableComponent,

    MyReservationsComponent,
    PendingReservationsComponent,
    ReservationsComponent
  ],
  providers: [],
  exports: [
    AddEventReservationFormComponent,
    AddSemesterFormComponent,
    AddSimpleReservationFormComponent,
    ClassReservationTableComponent,
    EventReservationTableComponent,
    EditClassReservationFormComponent,
    EditEventReservationFormComponent,
    ObserveClassReservationFormComponent,
    ObserveEventReservationFormComponent,
    FilterReservationFormComponent,

    ReservationTableComponent,

    MyReservationsComponent,
    PendingReservationsComponent,
    ReservationsComponent    
  ]
})
export class ReservationsModule {}
