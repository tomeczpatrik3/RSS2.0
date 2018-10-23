import { LOCALE_ID, Inject } from "@angular/core";
import { CalendarEventTitleFormatter, CalendarEvent } from "angular-calendar";
import { DatePipe } from "@angular/common";

/**
 * Az események címeinek formázását tartalmazó osztály
 */
export class CustomEventTitleFormatter extends CalendarEventTitleFormatter {
  constructor(@Inject(LOCALE_ID) private locale: string) {
    super();
  }

  /**
   * A havi nézet címeinek formázásáért felelős függvény
   * @param event Az esemény
   */
  month(event: CalendarEvent): string {
    return this.getTitle(event);
  }

  /**
   * A heti nézet címeinek formázásáért felelős függvény
   * @param event Az esemény
   */
  week(event: CalendarEvent): string {
    return this.getTitle(event);
  }

  /**
   * A napi nézet címeinek formázásáért felelős függvény
   * @param event Az esemény
   */
  day(event: CalendarEvent): string {
    return this.getTitle(event);
  }

  /**
   * A címek előállításáért felelős függvény
   * @param event Az esemény
   */
  private getTitle(event: CalendarEvent): string {
    if (!event.end) {
      return `<b>${new DatePipe(this.locale).transform(
        event.start,
        "HH:mm",
        this.locale
      )}</b> ${event.title}`;
    } else {
      return `<b>
      ${new DatePipe(this.locale).transform(event.start, "HH:mm", this.locale)}
      -
      ${new DatePipe(this.locale).transform(event.end, "HH:mm", this.locale)}
      </b> 
      ${event.title}`;
    }
  }
}
