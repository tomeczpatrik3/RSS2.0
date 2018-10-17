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
}
