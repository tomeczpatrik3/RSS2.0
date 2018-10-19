import {
  Component,
  ChangeDetectionStrategy,
  OnInit,
  ViewEncapsulation
} from "@angular/core";
import { map } from "rxjs/operators";
import {
  CalendarEvent,
  CalendarMonthViewDay,
  CalendarDateFormatter,
  CalendarEventTitleFormatter
} from "angular-calendar";
import { subDays, addDays } from "date-fns";
import { isSameMonth, isSameDay } from "date-fns";
import { Observable, of } from "rxjs";
import { colors } from "../../../utils/colors";

import { CustomDateFormatter } from "../../../providers/custom-date-formatter.provider";
import { CustomEventTitleFormatter } from "../../../providers/custom-event-title-formatter.provider";
import { CalendarService } from "../../../services/calendar.service";
import { ReservationCalendarEvent } from "../../../models/ReservationCalendarEvent";
import { AuthService } from "../../../authentication/auth.service";
import { Authorities } from "../../../config/authoritites.config";
import { ReservationType } from "../../../enums/ReservationType";
import { FormType } from "../../../enums/FormType";
import { DialogService } from "../../../services/dialog.service";

import { FormDialogComponent } from "../../dialogs/form-dialog/form-dialog.component";
import { ReservationInfo } from "../../../models/ReservationInfo";

@Component({
  selector: "app-calendar-full",
  templateUrl: "./calendar-full.component.html",
  styleUrls: ["./calendar-full.component.css"],
  /**
   * Saját CalendarDateFormatter használata
   * és CalendarEventTitleFormatter használata
   */
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter
    },
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter
    }
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class CalendarFullComponent implements OnInit {
  /**
   * Alapértelmezett nézet: havi
   */
  view: string = "month";

  /**
   * Magyar nyelv:
   */
  locale: string = "hu";

  /**
   * Speciális hosszúságú hét:
   */
  excludeDays: number[] = [0, 6];

  viewDate: Date = new Date();

  events$: Observable<Array<CalendarEvent<{ info: ReservationInfo }>>>;

  activeDayIsOpen: boolean = false;

  isAdmin: boolean = false;

  constructor(
    private calendarService: CalendarService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  ngOnInit(): void {
    this.isAdmin = this.authService.hasAuthority(Authorities.ROLE_ADMIN);
    this.fetchEvents();
  }

  /**
   * Az események betöltéséért felelős függvény:
   */
  fetchEvents(): void {
    let allowedActions = [
      {
        label: ' <i class="fa fa-sticky-note"></i>',
        onClick: ({ event }: { event: CalendarEvent }): void => {
          if (event.meta && event.meta.type === ReservationType.CLASS) {
            this.dialogService.openFormDialog(
              "Foglalás megtekintése:",
              FormType.OBSERVE_CLASS_RESERVATION_FORM,
              event.meta.id,
              FormDialogComponent
            );
          } else if (event.meta && event.meta.type === ReservationType.EVENT) {
            this.dialogService.openFormDialog(
              "Foglalás megtekintése:",
              FormType.OBSERVE_EVENT_RESERVATION_FORM,
              event.meta.id,
              FormDialogComponent
            );
          }
        }
      }
    ];
    if (this.isAdmin) {
      allowedActions.push({
        label: ' <i class="fa fa-edit"></i>',
        onClick: ({ event }: { event: CalendarEvent }): void => {
          if (event.meta && event.meta.type === ReservationType.CLASS) {
            this.dialogService.openFormDialog(
              "Foglalás szerkesztése:",
              FormType.EDIT_CLASS_RESERVATION_FORM,
              event.meta.id,
              FormDialogComponent
            );
          } else if (event.meta && event.meta.type === ReservationType.EVENT) {
            this.dialogService.openFormDialog(
              "Foglalás szerkesztése:",
              FormType.EDIT_EVENT_RESERVATION_FORM,
              event.meta.id,
              FormDialogComponent
            );
          }
        }
      });
    }

    this.events$ = this.calendarService.getEvents().pipe(
      map((results: ReservationCalendarEvent[]) => {
        return results.map((event: ReservationCalendarEvent) => {
          const info = event.info;
          return {
            title: event.title,
            start: new Date(event.start),
            end: new Date(event.end),
            color: colors.yellow,
            actions: allowedActions,
            meta: {
              info
            }
          };
        });
      })
    );
  }

  /**
   * A függvény ami akkor fut le, ha egy napra rákattintunk:
   * @param param0
   */
  dayClicked({
    date,
    events
  }: {
    date: Date;
    events: Array<CalendarEvent<{}>>;
  }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
        this.viewDate = date;
      }
    }
  }

  /**
   * A függvény ami akkor fut le, ha egy foglalásra rákattintunk:
   * @param event
   */
  //TODO: add (eventClicked)="eventClicked($event.event)" to the views.
  eventClicked(event: CalendarEvent<{}>): void {
    console.log(event);
  }

  /**
   * A havi nézet megjlenítése előtt rendereljük a táblázatot:
   * @param param0
   */
  beforeMonthViewRender({ body }: { body: CalendarMonthViewDay[] }): void {
    body.forEach(day => {
      if (day.date.getDate() % 2 === 1 && day.inMonth) {
        day.cssClass = "odd-cell";
      } else {
        day.cssClass = "even-cell";
      }
    });
  }

  /**
   * Napi nézetben a hétvégi napok kihagyása:
   * @param direction
   */
  skipWeekends(direction: "back" | "forward"): void {
    if (this.view === "day") {
      if (direction === "back") {
        while (this.excludeDays.indexOf(this.viewDate.getDay()) > -1) {
          this.viewDate = subDays(this.viewDate, 1);
        }
      } else if (direction === "forward") {
        while (this.excludeDays.indexOf(this.viewDate.getDay()) > -1) {
          this.viewDate = addDays(this.viewDate, 1);
        }
      }
    }
  }
}
