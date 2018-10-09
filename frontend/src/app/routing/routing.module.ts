import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { AppComponent } from "../components/app/app.component";
import { DashboardComponent } from "../components/dashboard/dashboard.component";
import { LoginFormComponent } from "../components/forms/login-form/login-form.component";
import { UserTableComponent } from "../components/tables/user-table/user-table.component";
import { ClassroomTableComponent } from "../components/tables/classroom-table/classroom-table.component";
import { SemesterTableComponent } from "../components/tables/semester-table/semester-table.component";
import { BuildingTableComponent } from "../components/tables/building-table/building-table.component";
import { ClassReservationTableComponent } from "../components/tables/class-reservation-table/class-reservation-table.component";
import { EventReservationTableComponent } from "../components/tables/event-reservation-table/event-reservation-table.component";

import { AddUserFormComponent } from "../components/forms/add-user-form/add-user-form.component";
import { AddClassroomFormComponent } from "../components/forms/add-classroom-form/add-classroom-form.component";
//import { AddReservationFormComponent } from '../components/forms/add-reservation-form/add-reservation-form.component';
import { SubjectTableComponent } from "../components/tables/subject-table/subject-table.component";
import { AddSubjectFormComponent } from "../components/forms/add-subject-form/add-subject-form.component";
import { AddBuildingFormComponent } from "../components/forms/add-building-form/add-building-form.component";
import { AddSemesterFormComponent } from "../components/forms/add-semester-form/add-semester-form.component";

import { AuthGuard } from "../guards/auth-guard.service";
import { RoleGuard } from "../guards/role-guard.service";
import { CanDeactivateGuard } from '../guards/can-deactivate-guard.service';

import { Authorities } from "../config/authoritites.config";

import { LogoutComponent } from "../components/logout/logout.component";
import { MyReservationsComponent } from "../components/other/my-reservations/my-reservations.component";
import { ReservationsComponent } from "../components/other/reservations/reservations.component";
import { AddEventReservationFormComponent } from "../components/forms/add-event-reservation-form/add-event-reservation-form.component";
import { AddSimpleReservationFormComponent } from "../components/forms/add-simple-reservation-form/add-simple-reservation-form.component";
import { AddSemesterReservationFormComponent } from "../components/forms/add-semester-reservation-form/add-semester-reservation-form.component";

const routes: Routes = [
  {
    path: "",
    redirectTo: "/dashboard",
    pathMatch: "full"
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
  {
    path: "login",
    component: LoginFormComponent
  },
  {
    path: "logout",
    component: LogoutComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "register",
    component: AddUserFormComponent
  },
  //-------
  //Tables:
  {
    path: "reservations",
    component: ReservationsComponent,
  },  
  {
    path: "class-reservations",
    component: ClassReservationTableComponent
  },
  {
    path: "event-reservations",
    component: EventReservationTableComponent
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
    path: "users",
    component: UserTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "classrooms",
    component: ClassroomTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "subjects",
    component: SubjectTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "buildings",
    component: BuildingTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "semesters",
    component: SemesterTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },

  //Forms:
  {
    path: "addUser",
    component: AddUserFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addClassroom",
    component: AddClassroomFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    },
  },
  {
    path: "addEventReservation",
    component: AddEventReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "addSimpleReservation",
    component: AddSimpleReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "addSemesterReservation",
    component: AddSemesterReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "addEventReservation",
    component: AddEventReservationFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_USER
    }
  },
  {
    path: "addSubject",
    component: AddSubjectFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addBuilding",
    component: AddBuildingFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addSemester",
    component: AddSemesterFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class RoutingModule {}
