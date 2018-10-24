import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./shared/components/dashboard/dashboard.component";

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

  {
    path: "users",
    loadChildren: "./modules/users/users.module#UsersModule"
  },

  {
    path: "buildings",
    loadChildren: "./modules/buildings/buildings.module#BuildingsModule"
  },

  {
    path: "classrooms",
    loadChildren: "./modules/classrooms/classrooms.module#ClassroomsModule"
  },

  {
    path: "semesters",
    loadChildren: "./modules/semesters/semesters.module#SemestersModule"
  },

  {
    path: "subjects",
    loadChildren: "./modules/subjects/subjects.module#SubjectsModule"
  },

  {
    path: "reservations",
    loadChildren: "./modules/reservations/reservations.module#ReservationsModule"
  },

  {
    path: "events",
    loadChildren: "./modules/events/events.module#EventsModule"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {}
