import { Day } from "../models/Day";
//Moment: a dátumok stringgé alakításához
import * as moment from 'moment/moment';

export class DateUtils {
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

  private static getDateTimeStr(date: Date, time: string): string {
    return `${moment(date).format("YYYY-MM-DD")} ${time}`;
  }
}
