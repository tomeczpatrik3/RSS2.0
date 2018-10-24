import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CalendarCardComponent } from './components/calendar-card/calendar-card.component';


const routes: Routes = [
  {
    path: "/reservations",
    component: CalendarCardComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalendarsRoutingModule { }