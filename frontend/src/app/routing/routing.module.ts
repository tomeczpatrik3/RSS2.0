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
    path: 'reservations',
    component: ReservationTableComponent
  },
  {
    path: 'subjects',
    component: SubjectTableComponent,
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