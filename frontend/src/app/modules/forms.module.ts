import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { MaterialModule } from "./material.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { ClassroomService } from "../services/classroom.service";
import { UserService } from "../services/user.service";
import { ClassReservationService } from "../services/class-reservation.service";
import { EventReservationService } from "../services/event-reservation.service";
import { SubjectService } from "../services/subject.service";
import { BuildingService } from "../services/building.service";
import { SemesterService } from "../services/semester.service";

import { AddClassroomFormComponent } from "../components/forms/add-classroom-form/add-classroom-form.component";
import { AddSubjectFormComponent } from "../components/forms/add-subject-form/add-subject-form.component";
import { AddUserFormComponent } from "../components/forms/add-user-form/add-user-form.component";
import { AddBuildingFormComponent } from "../components/forms/add-building-form/add-building-form.component";
import { AddSemesterFormComponent } from "../components/forms/add-semester-form/add-semester-form.component";
import { AddSimpleReservationFormComponent } from "../components/forms/add-simple-reservation-form/add-simple-reservation-form.component";
import { AddEventReservationFormComponent } from "../components/forms/add-event-reservation-form/add-event-reservation-form.component";
import { AddSemesterReservationFormComponent } from "../components/forms/add-semester-reservation-form/add-semester-reservation-form.component";

import { EditEventReservationFormComponent } from '../components/forms/edit-event-reservation-form/edit-event-reservation-form.component';

import { LoginFormComponent } from "../components/forms/login-form/login-form.component";

import { UniqueUsernameValidatorDirective } from "../directives/unique-username.directive";
import { UniqueBuildingNameValidatorDirective } from "../directives/unique-building-name.directive";
import { UniqueEventNameValidatorDirective } from "../directives/unique-event-name.directive";
import { TakenUsernameValidatorDirective } from "../directives/taken-username.directive";

@NgModule({
  imports: [CommonModule, MaterialModule, FormsModule, ReactiveFormsModule],
  declarations: [
    LoginFormComponent,
    AddClassroomFormComponent,
    AddSubjectFormComponent,
    AddUserFormComponent,
    AddBuildingFormComponent,
    AddSemesterFormComponent,
    AddSimpleReservationFormComponent,
    AddEventReservationFormComponent,
    AddSemesterReservationFormComponent,
    EditEventReservationFormComponent,

    //Directives:
    UniqueUsernameValidatorDirective,
    UniqueBuildingNameValidatorDirective,
    UniqueEventNameValidatorDirective,
    TakenUsernameValidatorDirective

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
    LoginFormComponent,
    AddClassroomFormComponent,
    AddSubjectFormComponent,
    AddUserFormComponent,
    AddBuildingFormComponent,
    AddSemesterFormComponent,
    AddSimpleReservationFormComponent,
    AddEventReservationFormComponent,
    AddSemesterReservationFormComponent,
    EditEventReservationFormComponent,
    FormsModule,
    ReactiveFormsModule
  ] //Amit akarunk, hogy más komponens használhasson
})
export class OwnFormsModule {}
