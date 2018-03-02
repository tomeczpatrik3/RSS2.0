import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Routes } from '../config/routes.config';
import { Subject } from '../models/Subject';
import { HttpService } from './http.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SubjectService {

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Subject[]> {
    return <Observable<Subject[]>>this.http.get(Routes.getUrl(Routes.SUBJECT));
  }

  getSubjectNames(): Observable<string[]> {
    return <Observable<string[]>>this.http.get(Routes.getUrl(Routes.SUBJECT_GET_SUBJECT_NAMES));
  }

  createSubject(subject: Subject): Observable<Subject> {
    return <Observable<Subject>>this.http.post(Routes.getUrl(Routes.SUBJECT_CREATE_SUBJECT), subject);
  }
}