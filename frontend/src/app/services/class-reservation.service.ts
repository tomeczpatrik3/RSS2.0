import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Routes } from "../config/routes.config";
import { Observable } from "rxjs/Observable";
import { ClassReservation } from "../models/ClassReservation";

/**
 * A tanórákra vonatkozó foglalásokhoz tartozó service osztály
 */
@Injectable()
export class ClassReservationService {
  constructor(private http: HttpClient) {}

  getAccepted(): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(Routes.getUrl(Routes.CLASS_RESERVATION_GET_ACCEPTED))
    );
  }

  findByUsername(username: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_USERNAME) +
          "/" +
          username
      )
    );
  }

  findByStatus(status: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_STATUS + "/" + status)
      )
    );
  }

  findByBuildingAndClassroom(
    building: string,
    classroom: string
  ): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM) +
          `?building=${building}&classroom=${classroom}`
      )
    );
  }

  findBySubjectCode(subjectCode: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_SUBJECT_CODE) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  findBySemester(semester: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_SEMESTER) +
          `?semester=${semester}`
      )
    );
  }

  setStatus(id: number, status: string): Observable<ClassReservation> {
    return <Observable<ClassReservation>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_SET_STATUS) +
          `?id=${id}&status=${status}`
      )
    );
  }

  createClassReservation(
    reservation: ClassReservation
  ): Observable<ClassReservation> {
    return <Observable<ClassReservation>>(
      this.http.post(
        Routes.getUrl(Routes.CLASS_RESERVATION_CREATE_RESERVATION),
        reservation
      )
    );
  }

  deleteByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_USERNAME) +
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
      this.http.delete(
        Routes.getUrl(
          Routes.CLASS_RESERVATION_DELETE_BY_BUILDING_AND_CLASSROOM
        ) + `?building=${building}&classroom=${classroom}`
      )
    );
  }

  deleteBySubjectCode(subjectCode: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_SUBJECT_CODE) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  deleteBySemester(semester: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_SEMESTER) +
          `?semester=${semester}`
      )
    );
  }

  deleteByStatus(status: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_STATUS) +
          `?status=${status}`
      )
    );
  }
}
