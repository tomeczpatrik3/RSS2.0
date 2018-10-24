import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { EventsRoutingModule } from "./events-routing.module";
import { CalendarFullComponent } from "./components/calendar-full/calendar-full.component";
import { CalendarHeaderComponent } from "./components/calendar-header/calendar-header.component";
import { FilterEventsFormComponent } from "./components/filter-events-form/filter-events-form.component";

import localeHu from "@angular/common/locales/hu";
import { registerLocaleData } from "@angular/common";

import { EventsDataService } from "./events.data.service";

import { CalendarModule, DateAdapter } from "angular-calendar";
import { adapterFactory } from "angular-calendar/date-adapters/date-fns";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CalendarPageComponent } from "./pages/calendar-page/calendar-page.component";
import { EventsPageComponent } from "./pages/events-page/events-page.component";
import { EventsTableComponent } from "./components/events-table/events-table.component";
import { PipeModule } from "../../shared/modules/pipe.module";


/**
 * A magyar nyelv beállítása:
 */
registerLocaleData(localeHu);

@NgModule({
  imports: [
    CommonModule,
    EventsRoutingModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    FormsModule,
    ReactiveFormsModule,
    PipeModule.forRoot(),
  ],
  declarations: [
    CalendarFullComponent,
    CalendarHeaderComponent,
    FilterEventsFormComponent,
    EventsTableComponent,

    CalendarPageComponent,
    EventsPageComponent
  ],
  providers: [
    EventsDataService
  ]
})
export class EventsModule {}
