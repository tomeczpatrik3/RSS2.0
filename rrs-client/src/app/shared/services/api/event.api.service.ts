import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { Event } from "../../models/Event";

/**
 * Az eseményekhez (foglalás) tartozó service osztály
 */
@Injectable()
export class EventApiService {
  constructor(private http: HttpClient) {}

  /**
   * Az események lekérdezéséért felelős függvény
   */
  getEvents(): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(ApiEndpoints.getUrl(ApiEndpoints.EVENT_GET_EVENTS))
    );
  }

  /**
   * Egy adott (teljes) névhez tartozó események lekérdezését megvalósító függvény
   * @param userName A teljes név
   */
  findByUserName(userName: string): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_USER_NAME) + `?userName=${userName}`
      )
    );
  }

  /**
   * Egy adott épülethez tartozó események lekérdezését megvalósító függvény
   * @param buildingName Az épület neve
   */
  findByBuildingName(
    buildingName: string
  ): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_BUILDING_NAME) +
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
  ): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_CLASSROOM_AND_BUILDING) +
          `?classroom=${classroom}&building=${building}`
      )
    );
  }

  /**
   * Egy adott (foglalás) névhez tartozó események lekérdezését megvalósító függvény
   * @param eventName A foglalás neve
   */
  findByEventName(eventName: string): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_EVENT_NAME) +
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
  ): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_SUBJECT_NAME) +
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
  ): Observable<Event[]> {
    return <Observable<Event[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_SEMESTER_NAME) +
          `?semesterName=${semesterName}`
      )
    );
  }
}
