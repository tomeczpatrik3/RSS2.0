import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReservationsRoutingModule } from "./reservations-routing.module";
import { AddEventReservationFormComponent } from "./components/add-event-reservation-form/add-event-reservation-form.component";
import { AddSimpleReservationFormComponent } from "./components/add-simple-reservation-form/add-simple-reservation-form.component";
import { ClassReservationTableComponent } from "./components/class-reservation-table/class-reservation-table.component";
import { EventReservationTableComponent } from "./components/event-reservation-table/event-reservation-table.component";
import { EditClassReservationFormComponent } from "./components/edit-class-reservation-form/edit-class-reservation-form.component";
import { EditEventReservationFormComponent } from "./components/edit-event-reservation-form/edit-event-reservation-form.component";
import { ObserveClassReservationFormComponent } from "./components/observe-class-reservation-form/observe-class-reservation-form.component";
import { ObserveEventReservationFormComponent } from "./components/observe-event-reservation-form/observe-event-reservation-form.component";
import { MyReservationsPageComponent } from "./pages/my-reservations-page/my-reservations-page.component";
import { PendingReservationsPageComponent } from "./pages/pending-reservations-page/pending-reservations-page.component";
import { AddSemesterReservationFormComponent } from "./components/add-semester-reservation-form/add-semester-reservation-form.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PipeModule } from "../../shared/modules/pipe.module";
import { ClassReservationsDataService } from "./class-reservations.data.service";
import { EventReservationsDataService } from "./event-reservations.data.service";
import { AddEventReservationPageComponent } from './pages/add-event-reservation-page/add-event-reservation-page.component';
import { AddSemesterReservationPageComponent } from './pages/add-semester-reservation-page/add-semester-reservation-page.component';
import { AddSimpleReservationPageComponent } from './pages/add-simple-reservation-page/add-simple-reservation-page.component';
import { ClassReservationPageComponent } from './pages/class-reservation-page/class-reservation-page.component';
import { EventReservationPageComponent } from './pages/event-reservation-page/event-reservation-page.component';

@NgModule({
  imports: [
    CommonModule,
    ReservationsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    PipeModule.forRoot(),
  ],
  declarations: [
    AddEventReservationFormComponent,
    AddSemesterReservationFormComponent,
    AddSimpleReservationFormComponent,
    ClassReservationTableComponent,
    EventReservationTableComponent,
    EditClassReservationFormComponent,
    EditEventReservationFormComponent,
    ObserveClassReservationFormComponent,
    ObserveEventReservationFormComponent,

    MyReservationsPageComponent,
    PendingReservationsPageComponent,
    AddEventReservationPageComponent,
    AddSimpleReservationPageComponent,
    ClassReservationPageComponent,
    EventReservationPageComponent,
    AddSemesterReservationPageComponent,
  ],
  providers: [
    ClassReservationsDataService,
    EventReservationsDataService
  ],
  exports: [
    EditClassReservationFormComponent,
    EditEventReservationFormComponent,
    ObserveClassReservationFormComponent,
    ObserveEventReservationFormComponent,
  ]
})
export class ReservationsModule {}
