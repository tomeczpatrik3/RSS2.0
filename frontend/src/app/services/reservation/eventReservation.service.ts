import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Routes } from "../../config/routes.config";
import { Observable } from "rxjs/Observable";
import { EventReservation } from "../../models/EventReservation";

/**
 * Az eseményre vonatkozó foglalásokhoz tartozó service osztály
 */
@Injectable()
export class EventReservationService {
  constructor(private http: HttpClient) {}

  getAccepted(): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(Routes.getUrl(Routes.EVENT_RESERVATION_GET_ACCEPTED))
    );
  }

  findByUsername(username: string): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_FIND_BY_USERNAME) +
          "/" +
          username
      )
    );
  }

  findByStatus(status: string): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_FIND_BY_STATUS + "/" + status)
      )
    );
  }

  findByBuildingAndClassroom(
    building: string,
    classroom: string
  ): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM) +
          `?building=${building}&classroom=${classroom}`
      )
    );
  }

  findByName(name: string): Observable<EventReservation[]> {
    return <Observable<EventReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_FIND_BY_NAME) + "/" + name
      )
    );
  }

  setStatus(id: number, status: string): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_SET_STATUS) +
          `?id=${id}&status=${status}`
      )
    );
  }

  createEventReservation(
    reservation: EventReservation
  ): Observable<EventReservation> {
    return <Observable<EventReservation>>(
      this.http.post(
        Routes.getUrl(Routes.EVENT_RESERVATION_CREATE_RESERVATION),
        reservation
      )
    );
  }

  deleteByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_DELETE_BY_USERNAME) +
          "/" +
          username
      )
    );
  }

  deleteByBuildingAndClassroom(
    building: string,
    classroom: string
  ): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(
          Routes.EVENT_RESERVATION_DELETE_BY_BUILDING_AND_CLASSROOM
        ) + `?building=${building}&classroom=${classroom}`
      )
    );
  }

  deleteByStatus(status: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_DELETE_BY_STATUS) +
          `?status=${status}`
      )
    );
  }

  deleteByName(name: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.EVENT_RESERVATION_DELETE_BY_NAME) + `?name=${name}`
      )
    );
  }
}
