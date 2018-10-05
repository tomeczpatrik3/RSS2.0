import { BaseReservation } from "./BaseReservation";
import {DateUtils} from "../utils/date-utils";
import { Semester } from "./Semester";

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

  public static buildSimple(
    username: string,
    building: string,
    classroom: string,
    note: string,
    semester: string,
    subjectCode: string,
    date: string,
    startTime: string,
    endTime: string
  ): ClassReservation {
    return new ClassReservation(
      username,
      building,
      classroom,
      "PENDING",
      note,
      semester,
      subjectCode,
      [`${date} ${startTime}`],
      [`${date} ${endTime}`]
    );
  }

  public static buildSemester(
    username: string,
    building: string,
    classroom: string,
    note: string,
    semester: Semester,
    subjectCode: string,
    day: string,
    startTime: string,
    endTime: string
  ): ClassReservation {
    
    return new ClassReservation(
      username,
      building,
      classroom,
      "PENDING",
      note,
      semester.name,
      subjectCode,
      DateUtils.getDates(semester.startDate, semester.endDate, day, startTime),
      DateUtils.getDates(semester.startDate, semester.endDate, day, endTime)
    );
  }
}
