import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Routes } from '../config/routes.config';
import { Subject } from '../models/Subject';
import { HttpService } from './http.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SubjectService {

  constructor(
    private http: HttpService
  ) { }

  getAll(): Observable<Subject[]> {
    return <Observable<Subject[]>>this.http.get(Routes.SUBJECT);
  }

  getSubjectNames(): Observable<string[]> {
    return <Observable<string[]>>this.http.get(Routes.SUBJECT_GET_SUBJECT_NAMES);
  }

  createSubject(subject: Subject): Observable<Subject> {
    return <Observable<Subject>>this.http.post(Routes.SUBJECT_CREATE_SUBJECT, subject);
  }
}