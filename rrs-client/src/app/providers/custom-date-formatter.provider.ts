import { CalendarDateFormatter, DateFormatterParams } from "angular-calendar";
import { getISOWeek } from "date-fns";
import { DatePipe } from "@angular/common";
import { Month } from "../enums/Month";
import { Day } from "../enums/Day";

/**
 * A dátumok formázását tartalmazó osztály
 */
export class CustomDateFormatter extends CalendarDateFormatter {
  /**
   * A havi nézet címe:
   * @param param0
   */
  public monthViewTitle({ date, locale }: DateFormatterParams): string {
    const year: string = new DatePipe(locale).transform(date, "y", locale);
    const month: string =
      Month[parseInt(new DatePipe(locale).transform(date, "MM", locale)) - 1];
    return `${year} - ${month}`;
  }

  /**
   * A heti nézet címe:
   * @param param0
   */
  public weekViewTitle({ date, locale }: DateFormatterParams): string {
    const year: string = new DatePipe(locale).transform(date, "y", locale);
    const weekNumber: number = getISOWeek(date);
    return `${year} - ${weekNumber}. hét`;
  }

  /**
   * A napi nézet címe:
   * @param param0
   */
  public dayViewTitle({ date, locale }: DateFormatterParams): string {
    const year: string = new DatePipe(locale).transform(date, "y", locale);
    const month: string =
      Month[parseInt(new DatePipe(locale).transform(date, "MM", locale)) - 1];
    const day: string = new DatePipe(locale).transform(date, "dd", locale);
    const dayName: string = Day[date.getDay()];

    return `${year}. ${month}. ${day}. - ${dayName}`;
  }
}
