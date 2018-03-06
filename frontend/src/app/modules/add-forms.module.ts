import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClassroomService } from '../services/classroom.service';
import { UserService } from '../services/user.service';
import { ReservationService } from '../services/reservation.service';
import { SubjectService } from '../services/subject.service';
import { BuildingService} from '../services/building.service';

import { AddClassroomFormComponent } from '../components/forms/add-classroom-form/add-classroom-form.component';
import { AddReservationFormComponent } from '../components/forms/add-reservation-form/add-reservation-form.component';
import { AddSubjectFormComponent } from '../components/forms/add-subject-form/add-subject-form.component';
import { AddUserFormComponent } from '../components/forms/add-user-form/add-user-form.component';
import { AddBuildingFormComponent } from '../components/forms/add-building-form/add-building-form.component';

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
    AddBuildingFormComponent
  ], //Komponensek, pipe-ok
  providers: [
      ClassroomService,
      UserService,
      ReservationService,
      SubjectService,
      BuildingService
  ], //Service(s)
  exports: [
    AddClassroomFormComponent,
    AddReservationFormComponent,
    AddSubjectFormComponent,
    AddUserFormComponent,
    AddBuildingFormComponent,
    FormsModule,
    ReactiveFormsModule
  ] //Amit akarunk, hogy más komponens használhasson
})
export class AddFormsModule
 {}