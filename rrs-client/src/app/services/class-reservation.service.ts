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

  /**
   * Az elfogadott foglalások lekérdezését megvalósító függvény
   */
  getAccepted(): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(Routes.getUrl(Routes.CLASS_RESERVATION_GET_ACCEPTED))
    );
  }

  /**
   * A várakozó foglalások lekérdezését megvalósító függvény
   */
  getPending(): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(Routes.getUrl(Routes.CLASS_RESERVATION_GET_PENDING))
    );
  }

  /**
   * A foglalások azonosító alapján történő lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<ClassReservation> {
    return <Observable<ClassReservation>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_ID) + `?id=${id}`
      )
    );
  }

  /**
   * A foglalások felhasználónév alapján történő lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  findByUsername(username: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_USERNAME) +
          "/" +
          username
      )
    );
  }

  /**
   * A foglalások státusz alapján történő lekérdezését megvalósító függvény
   * @param status A státusz
   */
  findByStatus(status: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_STATUS + "/" + status)
      )
    );
  }

  /**
   * A foglalások épület és tanterem alapján történő lekérdezését megvalósító függvény
   * @param building Az épület
   * @param classroom A tanterem
   */
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

  /**
   * A foglalások tárgykód alapján történő lekérdezését megvalósító függvény
   * @param subjectCode A tárgykód
   */
  findBySubjectCode(subjectCode: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_SUBJECT_CODE) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  /**
   * A foglalások szemeszter alapján történő lekérdezését megvalósító függvény
   * @param semester A szemeszter
   */
  findBySemester(semester: string): Observable<ClassReservation[]> {
    return <Observable<ClassReservation[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_FIND_BY_SEMESTER) +
          `?semester=${semester}`
      )
    );
  }

  /**
   * Egy adott foglalás státuszának beállításáért felelős függvény
   * @param id A foglalás azonosítója
   * @param status A beállítani kívánt státusz
   */
  setStatus(id: number, status: string): Observable<ClassReservation> {
    return <Observable<ClassReservation>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_SET_STATUS) +
          `?id=${id}&status=${status}`
      )
    );
  }

  /**
   * A foglalás létrehozásáért felelős függvény
   * @param reservation A foglalás objektum
   */
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

  /**
   * A felhasználónév alapján történő törlésért felelős függvény
   * @param username A felhasználónév
   */
  deleteByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_USERNAME) +
          "/" +
          username
      )
    );
  }

  /**
   * Az épület és tanterem alapján történő törlésért felelős függvény
   * @param building Az épület
   * @param classroom A tanterem
   */
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

  /**
   * A tárgykód alapján történő törlésért felelős függvény
   * @param subjectCode A tárgykód
   */
  deleteBySubjectCode(subjectCode: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_SUBJECT_CODE) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  /**
   * A szemeszter alapján történő törlésért felelős függvény
   * @param semester A fszemeszter
   */
  deleteBySemester(semester: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_SEMESTER) +
          `?semester=${semester}`
      )
    );
  }

  /**
   * A státusz alapján történő törlésért felelős függvény
   * @param status A foglalás státusza
   */
  deleteByStatus(status: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASS_RESERVATION_DELETE_BY_STATUS) +
          `?status=${status}`
      )
    );
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.CLASS_RESERVATION_EXISTS_BY_ID) + `?id=${id}`
      )
    );
  }
}