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

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
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
    path: 'users',
    component: UserTableComponent
  },  
  {
    path: 'classrooms',
    component: ClassroomTableComponent
  },
  {
    path: 'reservations',
    component: ReservationTableComponent
  },
  {
    path: 'subjects',
    component: SubjectTableComponent
  },
  {
    path: 'addUser',
    component: AddUserFormComponent
  },
  {
    path: 'addClassroom',
    component: AddClassroomFormComponent
  },
  {
    path: 'addReservation',
    component: AddReservationFormComponent
  },
  {
    path: 'addSubject',
    component: AddSubjectFormComponent
  },
  {
    path: 'filterReservations',
    component: FilterReservationFormComponent
  },
  

  /*
  {
    path: 'issues',
    component: IssueListComponent
  },
  */
//   {
//     path: 'issues/add',
//     component: IssueFormComponent
//   },
//   {
//     path: 'issues/:id',
//     component: IssueDetailComponent
//   },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [ RouterModule ],
  declarations: []
})
export class RoutingModule { }