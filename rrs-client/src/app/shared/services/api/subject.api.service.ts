import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Routes } from "../../config/routes.config";
import { Subject } from "../../models/Subject";
import { Observable } from "rxjs/Observable";

/**
 * Az tantárgyakhoz tartozó service osztály
 */
@Injectable()
export class SubjectApiService {
  constructor(private http: HttpClient) {}

  /**
   * A tantárgyak lekérdezéséért felelős függvény
   */
  getAll(): Observable<Subject[]> {
    return <Observable<Subject[]>>(
      this.http.get(Routes.getUrl(Routes.SUBJECT_GET_ALL))
    );
  }

  /**
   * A tantárgyak neveinek lekérdezéséért felelős függvény
   */
  getSubjectNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.SUBJECT_GET_SUBJECT_NAMES))
    );
  }

  /**
   * Egy adott tantárgyhoz tartozó (tárgy-) név lekérdezését megvalósító függvény
   * @param subjectCode A tárgykód
   */
  getSubjectName(subjectCode: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.SUBJECT_GET_SUBJECT_NAME) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  /**
   * A tantárgy létrehozásáért felelős függvény
   * @param subject A tantárgy objektum
   */
  createSubject(subject: Subject): Observable<Subject> {
    return <Observable<Subject>>(
      this.http.post(Routes.getUrl(Routes.SUBJECT_CREATE_SUBJECT), subject)
    );
  }

  /**
   * A tárgykód alapján történő törlésért felelős függvény
   * @param subjectCode A tárgykód
   */
  deleteByCode(subjectCode: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.SUBJECT_DELETE_BY_CODE) +
          `?subjectCode=${subjectCode}`
      )
    );
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(Routes.getUrl(Routes.SUBJECT_EXISTS_BY_ID) + `?id=${id}`)
    );
  }

  /**
   * A tárgykód alapján történő létezést ellenőrző függvény
   * @param code A tárgykód
   */
  existsByCode(code: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.SUBJECT_EXISTS_BY_CODE) + `?code=${code}`
      )
    );
  }
}