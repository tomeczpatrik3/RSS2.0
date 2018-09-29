import { BaseReservation } from "./BaseReservation";

/**
 * A tantárgy foglalás entitás
 */
export class ClassReservation extends BaseReservation {
  semester: string;
  subjectCode: string;
  startDates: string[];
  endDates: string[];

  public constructor(
    username: string,
    building: string,
    classroom: string,
    status: string,
    note: string,
    semester: string,
    subjectCode: string,
    startDates: string[],
    endDates: string[]
  ) {
    super(username, building, classroom, status, note);
    this.semester = semester;
    this.subjectCode = subjectCode;
    this.startDates = startDates;
    this.endDates = endDates;
  }
}
