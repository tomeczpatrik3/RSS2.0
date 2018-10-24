import { Day } from "../enums/Day";
import * as moment from "moment/moment";

/**
 * A dátumokkal kapcsolatos műveletekért felelős osztály
 */
export class DateUtils {
  /**
   * Egy szemeszterre vonatkozó foglalás számára 
   * a dátumok előállításáért felelős függvény
   * @param semesterStart A szemeszter kezdete
   * @param semesterEnd A szemeszter vége
   * @param day A foglalás napja (a hét egy napja)
   * @param time Az időpont
   */
  public static getDates(
    semesterStart: string,
    semesterEnd: string,
    day: string,
    time: string
  ): string[] {
    let endDate = new Date(semesterStart);
    let actDate = new Date(semesterEnd);
    let dates: string[] = [];

    for (actDate; actDate <= endDate; actDate.setDate(actDate.getDate() + 1)) {
      if (actDate.getDay() === Day[day].valueOf()) {
        dates.push(this.getDateTimeStr(actDate, time));
      }
    }

    console.log(dates);
    return dates;
  }

  /**
   * A DateTime string előállításáért felelős függvény
   * @param date A dátum
   * @param time Az idő szöveges formátumban
   */
  private static getDateTimeStr(date: Date, time: string): string {
    return `${moment(date).format("YYYY-MM-DD")} ${time}`;
  }
}
