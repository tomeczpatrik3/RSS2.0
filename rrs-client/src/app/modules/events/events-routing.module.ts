import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CalendarPageComponent } from './pages/calendar-page/calendar-page.component';
import { EventsPageComponent } from './pages/events-page/events-page.component';


const routes: Routes = [
  {
    path: "calendar",
    component: CalendarPageComponent
  },
  {
    path: "events",
    component: EventsPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventsRoutingModule { }