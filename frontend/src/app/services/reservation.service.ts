import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Reservation } from '../models/Reservation';
import { Routes } from '../config/routes.config';
import { HttpService } from './http.service';
import { Observable } from 'rxjs/Observable';
import { ReservationForm } from '../models/ReservationForm';
import { SimpleReservation } from '../models/SimpleReservation';
import { SemesterReservation } from '../models/SemesterReservation';
import { EventReservation } from '../models/EventReservation';

@Injectable()
export class ReservationService {

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.getUrl(Routes.RESERVATION));
  }

  findByUsername(username: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.getUrl(Routes.RESERVATION_FIND_BY_USERNAME) + "/" + username);
  }

  findByName(name: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.getUrl(Routes.RESERVATION_FIND_BY_NAME) + "/" + name);
  }

  findByRoomName(roomName: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.getUrl(Routes.RESERVATION_FIND_BY_ROOM_NAME) + "/" + roomName);
  }

  findByStatus(status: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.getUrl(Routes.RESERVATION_FIND_BY_STATUS + "/" + status));
  }

  setStatus(id: number, status: string): Observable<Reservation> {
    return <Observable<Reservation>>this.http.get(Routes.getUrl(Routes.RESERVATION_SET_STATUS)+"?id="+id+"&status="+status);
  }

  createSimpleReservation(reservation: SimpleReservation) {
    return <Observable<Reservation[]>>this.http.post(Routes.getUrl(Routes.RESERVATION_CREATE_SIMPLE), reservation);
  }

  createSemesterReservation(reservation: SemesterReservation) {
    return <Observable<Reservation[]>>this.http.post(Routes.getUrl(Routes.RESERVATION_CREATE_SEMESTER), reservation);
  }

  createEventReservation(reservation: EventReservation) {
    return <Observable<Reservation[]>>this.http.post(Routes.getUrl(Routes.RESERVATION_CREATE_EVENT), reservation);
  }

}
