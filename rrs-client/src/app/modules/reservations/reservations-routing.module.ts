import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddSemesterReservationFormComponent } from './components/add-semester-reservation-form/add-semester-reservation-form.component';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { AddSimpleReservationFormComponent } from './components/add-simple-reservation-form/add-simple-reservation-form.component';
import { AddEventReservationFormComponent } from './components/add-event-reservation-form/add-event-reservation-form.component';
import { EventReservationTableComponent } from './components/event-reservation-table/event-reservation-table.component';
import { ClassReservationTableComponent } from './components/class-reservation-table/class-reservation-table.component';
import { PendingReservationsComponent } from './pages/pending-reservations/pending-reservations.component';
import { MyReservationsComponent } from './pages/my-reservations/my-reservations.component';
import { ReservationsComponent } from './pages/reservations/reservations.component';


const routes: Routes = [
  //Pages:
  {
    path: "reservations",
    component: ReservationsComponent
  },
  {
    path: "myReservations",
    component: MyReservationsComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "pendingReservations",
    component: PendingReservationsComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  
  //Tables:
  {
    path: "tables/classReservations",
    component: ClassReservationTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "tables/eventReservations",
    component: EventReservationTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },

  //Forms:
  {
    path: "forms/addEventReservation",
    component: AddEventReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "forms/addSimpleReservation",
    component: AddSimpleReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "forms/addSemesterReservation",
    component: AddSemesterReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationsRoutingModule { }