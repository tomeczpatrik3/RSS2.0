import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddSemesterReservationFormComponent } from './components/add-semester-reservation-form/add-semester-reservation-form.component';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { MyReservationsPageComponent } from './pages/my-reservations-page/my-reservations-page.component';
import { PendingReservationsPageComponent } from './pages/pending-reservations-page/pending-reservations-page.component';
import { AddEventReservationPageComponent } from './pages/add-event-reservation-page/add-event-reservation-page.component';
import { AddSimpleReservationPageComponent } from './pages/add-simple-reservation-page/add-simple-reservation-page.component';
import { AddSemesterReservationPageComponent } from './pages/add-semester-reservation-page/add-semester-reservation-page.component';
import { EventReservationPageComponent } from './pages/event-reservation-page/event-reservation-page.component';
import { ClassReservationPageComponent } from './pages/class-reservation-page/class-reservation-page.component';


const routes: Routes = [
  {
    path: "myReservations",
    component: MyReservationsPageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "pendingReservations",
    component: PendingReservationsPageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "classReservations",
    component: ClassReservationPageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "eventReservations",
    component: EventReservationPageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addEventReservation",
    component: AddEventReservationPageComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "addSimpleReservation",
    component: AddSimpleReservationPageComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "addSemesterReservation",
    component: AddSemesterReservationPageComponent,
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