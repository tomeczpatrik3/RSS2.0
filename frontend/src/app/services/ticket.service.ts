import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Ticket } from '../models/Ticket';
import { Routes } from '../config/routes.config';

@Injectable()
export class TicketService {
  
  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Ticket[]> {
    return <Observable<Ticket[]>>this.http.get(Routes.getUrl(Routes.TICKET));
  }

  findByStatus(status: string): Observable<Ticket[]> {
    return <Observable<Ticket[]>>this.http.get(Routes.getUrl(Routes.TICKET_FIND_BY_STATUS + "/" + status));
  }

  findByUsername(username: string): Observable<Ticket[]> {
    return <Observable<Ticket[]>>this.http.get(Routes.getUrl(Routes.TICKET_FIND_BY_USERNAME + "/" + username));
  }

  setStatus(id: number, status: string): Observable<Ticket> {
    return <Observable<Ticket>>this.http.get(Routes.getUrl(Routes.TICKET_SET_STATUS)+"?id="+id+"&status="+status);
  }
}