import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./shared/components/dashboard/dashboard.component";

export const prefix: string = "web";

/**
 * Az elérési útvonalakat tartalmazó tömb
 */
const routes: Routes = [
  {
    path: "",
    redirectTo: `${prefix}/dashboard`,
    pathMatch: "full"
  },
  {
    path: `${prefix}/dashboard`,
    component: DashboardComponent
  },

  {
    path: `${prefix}/users`,
    loadChildren: "./modules/users/users.module#UsersModule"
  },

  {
    path: `${prefix}/buildings`,
    loadChildren: "./modules/buildings/buildings.module#BuildingsModule"
  },

  {
    path: `${prefix}/classrooms`,
    loadChildren: "./modules/classrooms/classrooms.module#ClassroomsModule"
  },

  {
    path: `${prefix}/semesters`,
    loadChildren: "./modules/semesters/semesters.module#SemestersModule"
  },

  {
    path: `${prefix}/subjects`,
    loadChildren: "./modules/subjects/subjects.module#SubjectsModule"
  },

  {
    path: `${prefix}/reservations`,
    loadChildren: "./modules/reservations/reservations.module#ReservationsModule"
  },

  {
    path: `${prefix}/events`,
    loadChildren: "./modules/events/events.module#EventsModule"
  },

  {
    path: `${prefix}/messages`,
    loadChildren: "./modules/messages/messages.module#MessagesModule"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {}
