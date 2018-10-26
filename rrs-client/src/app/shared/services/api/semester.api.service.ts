import { Injectable } from "@angular/core";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { Semester } from "../../models/Semester";

/**
 * Az szemeszterekhez tartozó service osztály
 */
@Injectable()
export class SemesterApiService {
  constructor(private http: HttpClient) {}

  /**
   * A szemeszterek lekérdezéséért felelős függvény
   */
  getAll(): Observable<Semester[]> {
    return <Observable<Semester[]>>(
      this.http.get(ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_GET_ALL))
    );
  }

  /**
   * A szemeszterek "neveinek" lekérdezéséért felelős függvény
   */
  getSemesterNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_GET_NAMES))
    );
  }

  /**
   * Egy adott szemeszter név alapján történő lekérdezését megvalósító függvény
   * @param name A szemeszter "neve"
   */
  findByName(name: string): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_FIND_BY_NAME) +
          `?name=${name}`
      )
    );
  }

  /**
   * A szemeszter létrehozásáért felelős függvény
   * @param semester A szemeszter objektum
   */
  createSemester(semester: Semester): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.post(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_CREATE_SEMESTER),
        semester
      )
    );
  }

  /**
   * Az szemeszter frissítéséért felelős függvény
   * @param id Az azonosító
   * @param semester A szemeszter
   */
  update(id: number, semester: Semester): Observable<Semester> {
    return <Observable<Semester>>(
      this.http.put(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_UPDATE) + `/${id}`,
        semester
      )
    );
  }

  /**
   * A név alapján történő törlésért felelős függvény
   * @param name A szemeszter "neve"
   */
  deleteByName(name: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_DELETE_BY_NAME) +
          `?name=${name}`
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
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_EXISTS_BY_ID) + `?id=${id}`
      )
    );
  }

  /**
   * A név alapján történő létezést ellenőrző függvény
   * @param name A szemeszter "neve"
   */
  existsByName(name: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_EXISTS_BY_NAME) +
          `?name=${name}`
      )
    );
  }
}
