import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { Observable } from "rxjs/Observable";
import { EventReservation } from "../../models/EventReservation";

/**
 * Az eseményre vonatkozó foglalásokhoz tartozó service osztály
 */
@Injectable()
export class EventReservationApiService {
  constructor(private http: HttpClient) {}

  /**
   * Az elfogadott foglalások lekérdezését megvalósító függvény
   */
  getAccepted(): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_GET_ACCEPTED)
      )
    );
  }

  /**
   * A várakozó foglalások lekérdezését megvalósító függvény
   */
  getPending(): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_GET_PENDING)
      )
    );
  }

  /**
   * A foglalások neveinek lekérdezését megvalósító függvény
   */
  getEventNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_GET_NAMES)
      )
    );
  }

  /**
   * A foglalások azonosító alapján történő lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_ID) +
          `?id=${id}`
      )
    );
  }

  /**
   * A foglalások felhasználónév alapján történő lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  findByUsername(username: string): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_USERNAME) +
          "/" +
          username
      )
    );
  }

  /**
   * A foglalások státusz alapján történő lekérdezését megvalósító függvény
   * @param status A státusz
   */
  findByStatus(status: string): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        ApiEndpoints.getUrl(
          ApiEndpoints.EVENT_RESERVATION_FIND_BY_STATUS + "/" + status
        )
      )
    );
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
    return <Observable<EventReservation[]>>(
      this.http.get(
        ApiEndpoints.getUrl(
          ApiEndpoints.EVENT_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM
        ) + `?building=${building}&classroom=${classroom}`
      )
    );
  }

  /**
   * A foglalások név alapján történő lekérdezését megvalósító függvény
   * @param name A foglalás neve
   */
  findByName(name: string): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_NAME) +
          "/" +
          name
      )
    );
  }

  /**
   * Egy adott foglalás státuszának beállításáért felelős függvény
   * @param id A foglalás azonosítója
   * @param status A beállítani kívánt státusz
   */
  setStatus(id: number, status: string): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_SET_STATUS) +
          `?id=${id}&status=${status}`
      )
    );
  }

  /**
   * A foglalás létrehozásáért felelős függvény
   * @param reservation A foglalás objektum
   */
  createEventReservation(
    reservation: EventReservation
  ): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.post(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_CREATE_RESERVATION),
        reservation
      )
    );
  }

  /**
   * Az foglalás frissítéséért felelős függvény
   * @param id Az azonosító
   * @param reservation A foglalás
   */
  update(
    id: number,
    reservation: EventReservation
  ): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.put(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_UPDATE) + `/${id}`,
        reservation
      )
    );
  }

  /**
   * Az azonosító alapján történő törlésért felelős függvény
   * @param id Az azonosító
   */
  deleteById(id: number): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_DELETE_BY_ID) +
          `?id=${id}`
      )
    );
  }

  /**
   * A felhasználónév alapján történő törlésért felelős függvény
   * @param username A felhasználónév
   */
  deleteByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_DELETE_BY_USERNAME) +
          "/" +
          username
      )
    );
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
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(
          ApiEndpoints.EVENT_RESERVATION_DELETE_BY_BUILDING_AND_CLASSROOM
        ) + `?building=${building}&classroom=${classroom}`
      )
    );
  }

  /**
   * A státusz alapján történő törlésért felelős függvény
   * @param status A foglalás státusza
   */
  deleteByStatus(status: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_DELETE_BY_STATUS) +
          `?status=${status}`
      )
    );
  }

  /**
   * A név alapján történő törlésért felelős függvény
   * @param name A foglalás neve
   */
  deleteByName(name: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_DELETE_BY_NAME) +
          `?name=${name}`
      )
    );
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_EXISTS_BY_ID) +
          `?id=${id}`
      )
    );
  }

  /**
   * A név alapján történő létezést ellenőrző függvény
   * @param name A foglalás neve
   */
  existsByName(name: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_EXISTS_BY_NAME) +
          `?name=${name}`
      )
    );
  }
}
