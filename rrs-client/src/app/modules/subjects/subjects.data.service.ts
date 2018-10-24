import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { SubjectApiService } from "../../shared/services/api/subject.api.service";
import { Subject } from "../../shared/models/Subject";

/**
 * Az tantárgyakhoz tartozó service osztály
 */
@Injectable()
export class SubjectsDataService {
  constructor(private api: SubjectApiService) {}

  /**
   * A tantárgyak lekérdezéséért felelős függvény
   */
  getAll(): Observable<Subject[]> {
    return this.api.getAll();
  }

  /**
   * A tantárgyak neveinek lekérdezéséért felelős függvény
   */
  getSubjectNames(): Observable<string[]> {
    return this.api.getSubjectNames();
  }

  /**
   * Egy adott tantárgyhoz tartozó (tárgy-) név lekérdezését megvalósító függvény
   * @param subjectCode A tárgykód
   */
  getSubjectName(subjectCode: string): Observable<any> {
    return this.api.getSubjectName(subjectCode);
  }

  /**
   * A tantárgy létrehozásáért felelős függvény
   * @param subject A tantárgy objektum
   */
  createSubject(subject: Subject): Observable<Subject> {
    return this.api.createSubject(subject);
  }

  /**
   * A tárgykód alapján történő törlésért felelős függvény
   * @param subjectCode A tárgykód
   */
  deleteByCode(subjectCode: string): Observable<any> {
    return this.api.deleteByCode(subjectCode);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }

  /**
   * A tárgykód alapján történő létezést ellenőrző függvény
   * @param code A tárgykód
   */
  existsByCode(code: string): Observable<boolean> {
    return this.api.existsByCode(code);
  }
}
