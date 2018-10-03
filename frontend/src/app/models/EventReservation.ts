import { BaseReservation } from "./BaseReservation";

/**
 * Az esemény foglalás entitás
 */
export class EventReservation extends BaseReservation {
  name: string;
  startDate: string;
  endDate: string;

  public constructor(
    username: string,
    building: string,
    classroom: string,
    status: string,
    note: string,
    eventName: string,
    startDate: string,
    endDate: string
  ) {
    super(username, building, classroom, status, note);
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public static build(
    username: string,
    building: string,
    classroom: string,
    note: string,
    eventName: string,
    date: string,
    startTime: string,
    endTime: string
  ): EventReservation {
    return new EventReservation(
      username,
      building,
      classroom,
      "PENDING",
      note,
      eventName,
      `${date} ${startTime}`,
      `${date} ${endTime}`
    );
  }
}
