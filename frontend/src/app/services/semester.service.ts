import { Injectable } from "@angular/core";
import { Routes } from "../config/routes.config";
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { Semester } from "../models/Semester";

/**
 * Az szemeszterekhez tartozó service osztály
 */
@Injectable()
export class SemesterService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Semester[]> {
    return <Observable<Semester[]>>(
      this.http.get(Routes.getUrl(Routes.SEMESTER_GET_ALL))
    );
  }

  getSemesterNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.SEMESTER_GET_NAMES))
    );
  }

  findByName(name: string): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.get(
        Routes.getUrl(Routes.SEMESTER_FIND_BY_NAME) + `?name=${name}`
      )
    );
  }

  createSemester(semester: Semester): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.post(Routes.getUrl(Routes.SEMESTER_CREATE_SEMESTER), semester)
    );
  }

  deleteByName(name: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.SEMESTER_DELETE_BY_NAME) + `?name=${name}`
      )
    );
  }

  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(Routes.getUrl(Routes.SEMESTER_EXISTS_BY_ID) + `?id=${id}`)
    );
  }

  existsByName(name: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.SEMESTER_EXISTS_BY_NAME) + `?name=${name}`
      )
    );
  }
}
