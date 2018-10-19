import { ReservationInfo } from "./ReservationInfo";

export class ReservationCalendarEvent {
  title: string;
  start: string;
  end: string;
  info: ReservationInfo

  constructor(
    title: string,
    start: string,
    end: string,
    info: ReservationInfo
  ) {
    this.title = title;
    this.start = start;
    this.end = end;
    this.info = info;
  }
}
