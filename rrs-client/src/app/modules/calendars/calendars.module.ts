import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { CalendarsRoutingModule } from "./calendars-routing.module";
import { CalendarCardComponent } from "./components/calendar-card/calendar-card.component";
import { CalendarFullComponent } from "./components/calendar-full/calendar-full.component";
import { CalendarHeaderComponent } from "./components/calendar-header/calendar-header.component";
import { FilterEventsFormComponent } from "./components/filter-events-form/filter-events-form.component";

import localeHu from "@angular/common/locales/hu";
import { registerLocaleData } from "@angular/common";

import { EventApiService } from "../../shared/services/api/event.api.service";
import { DialogService } from "../../shared/services/dialog.service";
import { AuthService } from "../../shared/services/auth.service";

import { CalendarModule, DateAdapter } from "angular-calendar";
import { adapterFactory } from "angular-calendar/date-adapters/date-fns";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

/**
 * A magyar nyelv beállítása:
 */
registerLocaleData(localeHu);

@NgModule({
  imports: [
    CommonModule,
    CalendarsRoutingModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    CalendarCardComponent,
    CalendarFullComponent,
    CalendarHeaderComponent,
    FilterEventsFormComponent
  ],
  providers: [EventApiService, DialogService, AuthService]
})
export class CalendarsModule {}
