import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { EventReservationApiService } from "../../shared/services/api/event-reservation.api.service";
import { EventReservation } from "../../shared/models/EventReservation";

/**
 * Az eseményre vonatkozó foglalásokhoz tartozó service osztály
 */
@Injectable()
export class EventReservationsDataService {
  constructor(private api: EventReservationApiService) {}

  /**
   * Az elfogadott foglalások lekérdezését megvalósító függvény
   */
  getAccepted(): Observable<EventReservation[]> {
    return this.api.getAccepted();
  }

  /**
   * A várakozó foglalások lekérdezését megvalósító függvény
   */
  getPending(): Observable<EventReservation[]> {
    return this.api.getPending();
  }

  /**
   * A foglalások neveinek lekérdezését megvalósító függvény
   */
  getEventNames(): Observable<string[]> {
    return this.api.getEventNames();
  }

  /**
   * A foglalások azonosító alapján történő lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<EventReservation> {
    return this.api.findById(id);
  }

  /**
   * A foglalások felhasználónév alapján történő lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  findByUsername(username: string): Observable<EventReservation[]> {
    return this.api.findByUsername(username);
  }

  /**
   * A foglalások státusz alapján történő lekérdezését megvalósító függvény
   * @param status A státusz
   */
  findByStatus(status: string): Observable<EventReservation[]> {
    return this.api.findByStatus(status);
  }

  /**
   * A foglalások épület és tanterem alapján történő lekérdezését megvalósító függvény
   * @param building Az épület
   * @param classroom A tanterem
   */
  findByBuildingAndClassroom(
    building: string,
    classroom: string
  ): Observable<EventReservation[]> {
    return this.api.findByBuildingAndClassroom(building, classroom);
  }

  /**
   * A foglalások név alapján történő lekérdezését megvalósító függvény
   * @param name A foglalás neve
   */
  findByName(name: string): Observable<EventReservation> {
    return this.api.findByName(name);
  }

  /**
   * Egy adott foglalás státuszának beállításáért felelős függvény
   * @param id A foglalás azonosítója
   * @param status A beállítani kívánt státusz
   */
  setStatus(id: number, status: string): Observable<EventReservation> {
    return this.api.setStatus(id, status);
  }

  /**
   * A foglalás létrehozásáért felelős függvény
   * @param reservation A foglalás objektum
   */
  createEventReservation(
    reservation: EventReservation
  ): Observable<EventReservation> {
    return this.api.createEventReservation(reservation);
  }

  /**
   * A foglalás frissítéséért felelős függvény
   * @param id Az azonosító
   * @param reservation A foglalás
   */
  update(
    id: number,
    reservation: EventReservation
  ): Observable<EventReservation> {
    return this.api.update(id, reservation);
  }

  /**
   * A saját foglalás frissítéséért felelős függvény
   * @param id Az azonosító
   * @param reservation A foglalás
   */
  updateOwnById(
    id: number,
    reservation: EventReservation
  ): Observable<EventReservation> {
    return this.api.updateOwnById(id, reservation);
  }

  /**
   * Az azonosító alapján történő törlésért felelős függvény
   * @param id Az azonosító
   */
  deleteById(id: number): Observable<any> {
    return this.api.deleteById(id);
  }

  /**
   * A felhasználónév alapján történő törlésért felelős függvény
   * @param username A felhasználónév
   */
  deleteByUsername(username: string): Observable<any> {
    return this.api.deleteByUsername(username);
  }

  /**
   * Az épület és tanterem alapján történő törlésért felelős függvény
   * @param building Az épület
   * @param classroom A tanterem
   */
  deleteByBuildingAndClassroom(
    building: string,
    classroom: string
  ): Observable<any> {
    return this.api.deleteByBuildingAndClassroom(building, classroom);
  }

  /**
   * A státusz alapján történő törlésért felelős függvény
   * @param status A foglalás státusza
   */
  deleteByStatus(status: string): Observable<any> {
    return this.api.deleteByStatus(status);
  }

  /**
   * A név alapján történő törlésért felelős függvény
   * @param name A foglalás neve
   */
  deleteByName(name: string): Observable<any> {
    return this.api.deleteByName(name);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }

  /**
   * A név alapján történő létezést ellenőrző függvény
   * @param name A foglalás neve
   */
  existsByName(name: string): Observable<boolean> {
    return this.api.existsByName(name);
  }
}
