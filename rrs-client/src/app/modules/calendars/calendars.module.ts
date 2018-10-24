import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { CalendarsRoutingModule } from "./calendars-routing.module";
import { CalendarCardComponent } from "./components/calendar-card/calendar-card.component";
import { CalendarFullComponent } from "./components/calendar-full/calendar-full.component";
import { CalendarHeaderComponent } from "./components/calendar-header/calendar-header.component";

import localeHu from "@angular/common/locales/hu";
import { registerLocaleData } from "@angular/common";

import { EventApiService } from "../../shared/services/api/event.api.service";
import { DialogService } from "../../shared/services/dialog.service";
import { AuthService } from "../../shared/services/auth.service";

import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { CalendarModule, DateAdapter } from "angular-calendar";
import { adapterFactory } from "angular-calendar/date-adapters/date-fns";

/**
 * A magyar nyelv beállítása:
 */
registerLocaleData(localeHu);

@NgModule({
  imports: [
    CommonModule,
    CalendarsRoutingModule,
    BrowserAnimationsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    })
  ],
  declarations: [
    CalendarCardComponent,
    CalendarFullComponent,
    CalendarHeaderComponent
  ],
  providers: [EventApiService, DialogService, AuthService]
})
export class CalendarsModule {}
