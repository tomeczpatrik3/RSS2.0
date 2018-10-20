import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Routes } from "../config/routes.config";
import { ReservationCalendarEvent } from "../models/ReservationCalendarEvent";

/**
 * Az épületekhez tartozó service osztály
 */
@Injectable()
export class CalendarService {
  constructor(private http: HttpClient) {}

  getEvents(): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(Routes.getUrl(Routes.CALENDAR_GET_EVENTS))
    );
  }

  findByUserName(userName: string): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.CALENDAR_FIND_BY_USER_NAME) +
          `?userName=${userName}`
      )
    );
  }

  findByBuildingName(buildingName: string): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.CALENDAR_FIND_BY_BUILDING_NAME) +
          `?buildingName=${buildingName}`
      )
    );
  }

  findByClassroomName(classroomName: string): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.CALENDAR_FIND_BY_CLASSROOM_NAME) +
          `?classroomName=${classroomName}`
      )
    );
  }

  findByEventName(eventName: string): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.CALENDAR_FIND_BY_EVENT_NAME) +
          `?eventName=${eventName}`
      )
    );
  }

  findBySubjectName(subjectName: string): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.CALENDAR_FIND_BY_SUBJECT_NAME) +
          `?subjectName=${subjectName}`
      )
    );
  }

  findBySemesterName(semesterName: string): Observable<ReservationCalendarEvent[]> {
    return <Observable<ReservationCalendarEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.CALENDAR_FIND_BY_SEMESTER_NAME) +
          `?semesterName=${semesterName}`
      )
    );
  }
}
