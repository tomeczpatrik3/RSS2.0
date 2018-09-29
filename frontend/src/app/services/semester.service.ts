import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Routes } from "../config/routes.config";
import { Semester } from "../models/Semester";
import { Observable } from "rxjs/observable";

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

  createSemester(semester: Semester): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.post(Routes.getUrl(Routes.SEMESTER_CREATE_SEMESTER), semester)
    );
  }

  deleteByName(name: string): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.post(Routes.getUrl(Routes.SEMESTER_DELETE_BY_NAME), name)
    );
  }
}
