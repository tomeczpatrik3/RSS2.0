import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClassroomService } from '../services/classroom.service';
import { UserService } from '../services/user.service';
import { ClassReservationService } from '../services/classReservation.service';
import { EventReservationService } from '../services/eventReservation.service';
import { SubjectService } from '../services/subject.service';
import { BuildingService} from '../services/building.service';
import { SemesterService } from '../services/semester.service';

import { AddClassroomFormComponent } from '../components/forms/add-classroom-form/add-classroom-form.component';
import { AddReservationFormComponent } from '../components/forms/add-reservation-form/add-reservation-form.component';
import { AddSubjectFormComponent } from '../components/forms/add-subject-form/add-subject-form.component';
import { AddUserFormComponent } from '../components/forms/add-user-form/add-user-form.component';
import { AddBuildingFormComponent } from '../components/forms/add-building-form/add-building-form.component';
import { AddSemesterFormComponent } from '../components/forms/add-semester-form/add-semester-form.component';
import { AddSimpleReservationComponent } from '../components/forms/add-simple-reservation/add-simple-reservation.component';
import { AddEventReservationComponent } from '../components/forms/add-event-reservation/add-event-reservation.component';
import { AddSemesterReservationComponent } from '../components/forms/add-semester-reservation/add-semester-reservation.component';

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
      ClassReservationService,
      EventReservationService,
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