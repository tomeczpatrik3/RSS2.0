import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Routes } from "../config/routes.config";
import { Subject } from "../models/Subject";
import { Observable } from "rxjs/Observable";

/**
 * Az tantárgyakhoz tartozó service osztály
 */
@Injectable()
export class SubjectService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Subject[]> {
    return <Observable<Subject[]>>(
      this.http.get(Routes.getUrl(Routes.SUBJECT_GET_ALL))
    );
  }

  getSubjectNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.SUBJECT_GET_SUBJECT_NAMES))
    );
  }

  getSubjectName(subjectCode: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.SUBJECT_GET_SUBJECT_NAME) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  createSubject(subject: Subject): Observable<Subject> {
    return <Observable<Subject>>(
      this.http.post(Routes.getUrl(Routes.SUBJECT_CREATE_SUBJECT), subject)
    );
  }

  deleteByCode(subjectCode: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.SUBJECT_DELETE_BY_CODE) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(Routes.getUrl(Routes.SUBJECT_EXISTS_BY_ID) + `?id=${id}`)
    );
  }

  existsByCode(code: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.SUBJECT_EXISTS_BY_CODE) + `?code=${code}`
      )
    );
  }
}
