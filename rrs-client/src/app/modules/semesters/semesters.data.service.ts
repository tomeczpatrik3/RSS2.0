import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { SemesterApiService } from "../../shared/services/api/semester.api.service";
import { Semester } from "../../shared/models/Semester";

/**
 * Az szemeszterekhez tartozó service osztály
 */
@Injectable()
export class SemesterDataService {
  constructor(private api: SemesterApiService) {}

  /**
   * A szemeszterek lekérdezéséért felelős függvény
   */
  getAll(): Observable<Semester[]> {
    return this.api.getAll();
  }

  /**
   * A szemeszterek "neveinek" lekérdezéséért felelős függvény
   */
  getSemesterNames(): Observable<string[]> {
    return this.api.getSemesterNames();
  }

  /**
   * Egy adott azonosító alapján történő lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<Semester> {
    return this.api.findById(id);
  }

  /**
   * Egy adott szemeszter név alapján történő lekérdezését megvalósító függvény
   * @param name A szemeszter "neve"
   */
  findByName(name: string): Observable<Semester> {
    return this.api.findByName(name);
  }

  /**
   * A szemeszter létrehozásáért felelős függvény
   * @param semester A szemeszter objektum
   */
  createSemester(semester: Semester): Observable<Semester> {
    return this.api.createSemester(semester);
  }

  /**
   * Az szemeszter frissítéséért felelős függvény
   * @param id Az azonosító
   * @param semester A szemeszter
   */
  update(id: number, semester: Semester): Observable<Semester> {
    return this.api.update(id, semester);
  }

  /**
   * A név alapján történő törlésért felelős függvény
   * @param name A szemeszter "neve"
   */
  deleteByName(name: string): Observable<any> {
    return this.api.deleteByName(name);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }

  /**
   * A név alapján történő létezést ellenőrző függvény
   * @param name A szemeszter "neve"
   */
  existsByName(name: string): Observable<boolean> {
    return this.api.existsByName(name);
  }
}
