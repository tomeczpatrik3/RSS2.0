import { NgModule } from '@angular/core';
import { RouterModule, Routes }   from '@angular/router';

import { AppComponent } from '../components/app/app.component';
import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { LoginFormComponent } from '../components/forms/login-form/login-form.component';
import { UserTableComponent } from '../components/tables/user-table/user-table.component';
import { ClassroomTableComponent } from '../components/tables/classroom-table/classroom-table.component';
import { ReservationTableComponent } from '../components/tables/reservation-table/reservation-table.component';
import { AddUserFormComponent } from '../components/forms/add-user-form/add-user-form.component';
import { AddClassroomFormComponent } from '../components/forms/add-classroom-form/add-classroom-form.component';
import { AddReservationFormComponent } from '../components/forms/add-reservation-form/add-reservation-form.component';
import { SubjectTableComponent } from '../components/tables/subject-table/subject-table.component';
import { AddSubjectFormComponent } from '../components/forms/add-subject-form/add-subject-form.component';
import { FilterReservationFormComponent } from '../components/forms/filter-reservation-form/filter-reservation-form.component';

import { AuthGuardService as AuthGuard } from '../guards/auth-guard.service';
import { RoleGuardService as RoleGuard } from '../guards/role-guard.service';
import { FormGuardService as FormGuard } from '../guards/form-guard.service';

import { Authorities } from '../config/authoritites.config';
import { LogoutComponent } from '../components/logout/logout.component';
import { AddBuildingFormComponent } from '../components/forms/add-building-form/add-building-form.component';
import { MyReservationsTableComponent } from '../components/tables/my-reservations-table/my-reservations-table.component';
import { BuildingTableComponent } from '../components/tables/building-table/building-table.component';
import { DetailedReservationsComponent } from '../components/detailed-reservations/detailed-reservations.component';
import { PendingReservationsTableComponent } from '../components/tables/pending-reservations-table/pending-reservations-table.component';
import { PendingReservationsComponent } from '../components/pending-reservations/pending-reservations.component';
import { AddSemesterFormComponent } from '../components/forms/add-semester-form/add-semester-form.component';
import { SemesterTableComponent } from '../components/tables/semester-table/semester-table.component';
import { DailyViewComponent } from '../components/tables/reservation/daily-view/daily-view.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_USER
    } 
  },
  //-------
  //Tables:
  {
    path: 'reservations',
    component: ReservationTableComponent
  },
  {
    path: 'myReservations',
    component: MyReservationsTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_USER
    } 
  },
  {
    path: 'myReservationsDetailed',
    component: DetailedReservationsComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_USER
    } 
  },
  {
    path: 'users',
    component: UserTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },  
  {
    path: 'classrooms',
    component: ClassroomTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  {
    path: 'subjects',
    component: SubjectTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  {
    path: 'buildings',
    component: BuildingTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  {
    path: 'semesters',
    component: SemesterTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  {
    path: 'dailyView',
    component: DailyViewComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  {
    path: 'pendingReservations',
    component: PendingReservationsTableComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  //--------
  //Forms:
  {
    path: 'addUser',
    component: AddUserFormComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    } 
  },
  {
    path: 'addClassroom',
    component: AddClassroomFormComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    },
    canDeactivate:[FormGuard] 
  },
  {
    path: 'addReservation',
    component: AddReservationFormComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_USER
    },
  },
  {
    path: 'addSubject',
    component: AddSubjectFormComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    },
  },
  {
    path: 'addBuilding',
    component: AddBuildingFormComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    },
  },
  {
    path: 'addSemester',
    component: AddSemesterFormComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    },
  },
  {
    path: 'managePendingReservations',
    component: PendingReservationsComponent,
    canActivate: [RoleGuard], 
    data: { 
      authority: Authorities.ROLE_ADMIN
    },
  },
  {
    path: 'filterReservations',
    component: FilterReservationFormComponent
  },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [ RouterModule ],
  declarations: []
})
export class RoutingModule { }