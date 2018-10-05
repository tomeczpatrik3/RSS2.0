import { Day } from "../models/Day";

export class DateUtils {
  public static getDates(semesterStart: string, semesterEnd: string, day: string, time: string): string[] {
    let endDate = new Date(semesterStart);
    let actDate = new Date(semesterEnd);
    let startDates: string[];
    
    for (actDate; actDate <= endDate; actDate.setDate(actDate.getDate() + 1)) {
      if (actDate.getDay() === Day[day].valueOf()) {
        startDates.push(this.getDateTimeStr(actDate, time));
      }
    }

    return startDates;
  }

  private static getDateTimeStr(date: Date, time: string): string {
    return `${date.getUTCFullYear}-${date.getUTCMonth}-${date.getUTCDay} ${time}`;
  }
}
