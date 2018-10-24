import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Routes } from "../../config/routes.config";
import { ReservationEvent } from "../../models/ReservationEvent";

/**
 * Az eseményekhez (foglalás) tartozó service osztály
 */
@Injectable()
export class EventApiService {
  constructor(private http: HttpClient) {}

  /**
   * Az események lekérdezéséért felelős függvény
   */
  getEvents(): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(Routes.getUrl(Routes.EVENT_GET_EVENTS))
    );
  }

  /**
   * Egy adott (teljes) névhez tartozó események lekérdezését megvalósító függvény
   * @param userName A teljes név
   */
  findByUserName(userName: string): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_FIND_BY_USER_NAME) + `?userName=${userName}`
      )
    );
  }

  /**
   * Egy adott épülethez tartozó események lekérdezését megvalósító függvény
   * @param buildingName Az épület neve
   */
  findByBuildingName(
    buildingName: string
  ): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_FIND_BY_BUILDING_NAME) +
          `?buildingName=${buildingName}`
      )
    );
  }

  /**
   * Egy adott épülethez és tanteremhez tartozó események lekérdezését megvalósító függvény
   * @param classroom A tanterem neve
   * @param building Az épület neve
   */
  findByClassroomAndBuilding(
    classroom: string,
    building: string
  ): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_FIND_BY_CLASSROOM_AND_BUILDING) +
          `?classroom=${classroom}&building=${building}`
      )
    );
  }

  /**
   * Egy adott (foglalás) névhez tartozó események lekérdezését megvalósító függvény
   * @param eventName A foglalás neve
   */
  findByEventName(eventName: string): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_FIND_BY_EVENT_NAME) +
          `?eventName=${eventName}`
      )
    );
  }

  /**
   * Egy adott tantárgyhoz tartozó események lekérdezését megvalósító függvény
   * @param subjectName A tantárgy neve
   */
  findBySubjectName(
    subjectName: string
  ): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_FIND_BY_SUBJECT_NAME) +
          `?subjectName=${subjectName}`
      )
    );
  }

  /**
   * Egy adott szemeszterhez tartozó események lekérdezését megvalósító függvény
   * @param semesterName A szemeszter "neve"
   */
  findBySemesterName(
    semesterName: string
  ): Observable<ReservationEvent[]> {
    return <Observable<ReservationEvent[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_FIND_BY_SEMESTER_NAME) +
          `?semesterName=${semesterName}`
      )
    );
  }
}
