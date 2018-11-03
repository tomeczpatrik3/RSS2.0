import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { EventApiService } from "../../shared/services/api/event.api.service";
import { Event } from "../../shared/models/Event";

/**
 * Az eseményekhez (foglalás) tartozó service osztály
 */
@Injectable()
export class EventsDataService {
  constructor(private api: EventApiService) {}

  /**
   * Az események lekérdezéséért felelős függvény
   */
  getEvents(): Observable<Event[]> {
    return this.api.getEvents();
  }

  /**
   * Egy adott (teljes) névhez tartozó események lekérdezését megvalósító függvény
   * @param userName A teljes név
   */
  findByUserName(userName: string): Observable<Event[]> {
    return this.api.findByUserName(userName);
  }

  /**
   * Egy adott épülethez tartozó események lekérdezését megvalósító függvény
   * @param buildingName Az épület neve
   */
  findByBuildingName(
    buildingName: string
  ): Observable<Event[]> {
    return this.api.findByBuildingName(buildingName);
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
    return this.api.findByClassroomAndBuilding(classroom, building);
  }

  /**
   * Egy adott (foglalás) névhez tartozó események lekérdezését megvalósító függvény
   * @param eventName A foglalás neve
   */
  findByEventName(eventName: string): Observable<Event[]> {
    return this.api.findByEventName(eventName);
  }

  /**
   * Egy adott tantárgyhoz tartozó események lekérdezését megvalósító függvény
   * @param subjectName A tantárgy neve
   */
  findBySubjectName(
    subjectName: string
  ): Observable<Event[]> {
    return this.api.findBySubjectName(subjectName);
  }

  /**
   * Egy adott szemeszterhez tartozó események lekérdezését megvalósító függvény
   * @param semesterName A szemeszter "neve"
   */
  findBySemesterName(
    semesterName: string
  ): Observable<Event[]> {
    return this.api.findBySemesterName(semesterName);
  }
}
