import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

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
import { SubjectTableComponent } from "../components/tables/subject-table/subject-table.component";
import { AddSubjectFormComponent } from "../components/forms/add-subject-form/add-subject-form.component";
import { AddBuildingFormComponent } from "../components/forms/add-building-form/add-building-form.component";
import { AddSemesterFormComponent } from "../components/forms/add-semester-form/add-semester-form.component";
import { AddEventReservationFormComponent } from "../components/forms/add-event-reservation-form/add-event-reservation-form.component";
import { AddSimpleReservationFormComponent } from "../components/forms/add-simple-reservation-form/add-simple-reservation-form.component";
import { AddSemesterReservationFormComponent } from "../components/forms/add-semester-reservation-form/add-semester-reservation-form.component";

import { RoleGuard } from "../guards/role-guard.service";
import { CanDeactivateGuard } from "../guards/can-deactivate-guard.service";

import { Authorities } from "../config/authoritites.config";

import { MyReservationsComponent } from "../components/other/my-reservations/my-reservations.component";
import { ReservationsComponent } from "../components/other/reservations/reservations.component";
import { PendingReservationsComponent } from "../components/other/pending-reservations/pending-reservations.component";
import { CalendarCardComponent } from "../components/calendar/calendar-card/calendar-card.component";

/**
 * Az elérési útvonalakat tartalmazó tömb
 */
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

  //User things:
  {
    path: "login",
    component: LoginFormComponent
  },
  {
    path: "register",
    component: AddUserFormComponent
  },

  //Components:
  {
    path: "reservationsCalendar",
    component: CalendarCardComponent
  },
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

  //-------
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
  {
    path: "tables/users",
    component: UserTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "tables/classrooms",
    component: ClassroomTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "tables/subjects",
    component: SubjectTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "tables/buildings",
    component: BuildingTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "tables/semesters",
    component: SemesterTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },

  //Forms:
  {
    path: "forms/addUser",
    component: AddUserFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "forms/addClassroom",
    component: AddClassroomFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
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
    path: "forms/addSubject",
    component: AddSubjectFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "forms/addBuilding",
    component: AddBuildingFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "forms/addSemester",
    component: AddSemesterFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class RoutingModule {}
