import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClassroomService } from '../services/classroom.service';
import { UserService } from '../services/user.service';
import { ReservationService } from '../services/reservation.service';
import { SubjectService } from '../services/subject.service';
import { BuildingService} from '../services/building.service';
import { SemesterService } from '../services/semester.service';

import { AddClassroomFormComponent } from '../components/classroom/add-classroom-form/add-classroom-form.component';
import { AddReservationFormComponent } from '../components/reservation/add-reservation-form/add-reservation-form.component';
import { AddSubjectFormComponent } from '../components/subject/add-subject-form/add-subject-form.component';
import { AddUserFormComponent } from '../components/user/add-user-form/add-user-form.component';
import { AddBuildingFormComponent } from '../components/building/add-building-form/add-building-form.component';
import { AddSemesterFormComponent } from '../components/semester/add-semester-form/add-semester-form.component';
import { AddSimpleReservationComponent } from '../components/reservation/add-simple-reservation/add-simple-reservation.component';
import { AddEventReservationComponent } from '../components/reservation/add-event-reservation/add-event-reservation.component';
import { AddSemesterReservationComponent } from '../components/reservation/add-semester-reservation/add-semester-reservation.component';
import { AddReservationBaseComponent } from '../components/reservation/add-reservaion-base.component';


@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddClassroomFormComponent,
    AddReservationFormComponent,
    AddSubjectFormComponent,
    AddUserFormComponent,
    AddBuildingFormComponent,
    AddSemesterFormComponent,
    AddSimpleReservationComponent,
    AddEventReservationComponent,
    AddSemesterReservationComponent,
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
    AddClassroomFormComponent,
    AddReservationFormComponent,
    AddSubjectFormComponent,
    AddUserFormComponent,
    AddBuildingFormComponent,
    AddSemesterFormComponent,
    AddSimpleReservationComponent,
    AddEventReservationComponent,
    AddSemesterReservationComponent,
    FormsModule,
    ReactiveFormsModule
  ] //Amit akarunk, hogy más komponens használhasson
})
export class AddFormsModule
 {}