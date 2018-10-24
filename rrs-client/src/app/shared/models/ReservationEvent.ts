import { ReservationInfo } from "./ReservationInfo";

/**
 * A foglalás esemény entitás
 */
export class ReservationEvent {
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
