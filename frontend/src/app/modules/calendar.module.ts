import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import localeHu from '@angular/common/locales/hu';
import { registerLocaleData } from '@angular/common';

import { CalendarHeaderComponent } from '../components/calendar/calendar-header/calendar-header.component';
import { CalendarFullComponent } from '../components/calendar/calendar-full/calendar-full.component';
import { CalendarCardComponent } from '../components/calendar/calendar-card/calendar-card.component';


/**
 * A magyar nyelv beállítása:
 */
registerLocaleData(localeHu);

@NgModule({
  imports: [
    BrowserAnimationsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    })
  ],
  declarations: [
    CalendarHeaderComponent,
    CalendarFullComponent,
    CalendarCardComponent
  ],
  providers: [
  ],
  exports: [
    CalendarHeaderComponent,
    CalendarFullComponent,
    CalendarCardComponent
  ]
})
export class OwnCalendarModule {}