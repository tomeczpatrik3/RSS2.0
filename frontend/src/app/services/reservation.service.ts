import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Reservation } from '../models/Reservation';
import { Routes } from '../config/routes.config';
import { HttpService } from './http.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReservationService {

  constructor(
    private http: HttpService
  ) { }

  getAll(): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.RESERVATION);
  }

  findByUsername(username: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.RESERVATION_FIND_BY_USERNAME, username);
  }

  findByName(name: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.RESERVATION_FIND_BY_NAME, name);
  }

  findByRoomName(roomName: string): Observable<Reservation[]> {
    return <Observable<Reservation[]>>this.http.get(Routes.RESERVATION_FIND_BY_ROOM_NAME, roomName);
  }

  createRes(res: Reservation) {
    return <Observable<Reservation[]>>this.http.post(Routes.RESERVATION_CREATE_RES, res);
  }

}
