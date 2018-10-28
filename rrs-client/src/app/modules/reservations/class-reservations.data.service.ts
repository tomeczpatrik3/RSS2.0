import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { ClassReservationApiService } from "../../shared/services/api/class-reservation.api.service";
import { ClassReservation } from "../../shared/models/ClassReservation";

/**
 * A tanórákra vonatkozó foglalásokhoz tartozó service osztály
 */
@Injectable()
export class ClassReservationsDataService {
  constructor(private api: ClassReservationApiService) {}

  /**
   * Az elfogadott foglalások lekérdezését megvalósító függvény
   */
  getAccepted(): Observable<ClassReservation[]> {
    return this.api.getAccepted();
  }

  /**
   * A várakozó foglalások lekérdezését megvalósító függvény
   */
  getPending(): Observable<ClassReservation[]> {
    return this.api.getPending();
  }

  /**
   * A foglalások azonosító alapján történő lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<ClassReservation> {
    return this.api.findById(id);
  }

  /**
   * A foglalások felhasználónév alapján történő lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  findByUsername(username: string): Observable<ClassReservation[]> {
    return this.api.findByUsername(username);
  }

  /**
   * A foglalások státusz alapján történő lekérdezését megvalósító függvény
   * @param status A státusz
   */
  findByStatus(status: string): Observable<ClassReservation[]> {
    return this.api.findByStatus(status);
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
    return this.api.findByBuildingAndClassroom(building, classroom);
  }

  /**
   * A foglalások tárgykód alapján történő lekérdezését megvalósító függvény
   * @param subjectCode A tárgykód
   */
  findBySubjectCode(subjectCode: string): Observable<ClassReservation[]> {
    return this.api.findBySubjectCode(subjectCode);
  }

  /**
   * A foglalások szemeszter alapján történő lekérdezését megvalósító függvény
   * @param semester A szemeszter
   */
  findBySemester(semester: string): Observable<ClassReservation[]> {
    return this.api.findBySemester(semester);
  }

  /**
   * Egy adott foglalás státuszának beállításáért felelős függvény
   * @param id A foglalás azonosítója
   * @param status A beállítani kívánt státusz
   */
  setStatus(id: number, status: string): Observable<ClassReservation> {
    return this.api.setStatus(id, status);
  }

  /**
   * A foglalás létrehozásáért felelős függvény
   * @param reservation A foglalás objektum
   */
  createClassReservation(
    reservation: ClassReservation
  ): Observable<ClassReservation> {
    return this.api.createClassReservation(reservation);
  }

  /**
   * Az foglalás frissítéséért felelős függvény
   * @param id Az azonosító
   * @param reservation A foglalás
   */
  update(
    id: number,
    reservation: ClassReservation
  ): Observable<ClassReservation> {
    return this.api.update(id, reservation);
  }

  /**
   * A felhasználónév alapján történő törlésért felelős függvény
   * @param username A felhasználónév
   */
  deleteByUsername(username: string): Observable<any> {
    return this.api.deleteByUsername(username);
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
    return this.api.deleteByBuildingAndClassroom(building, classroom);
  }

  /**
   * A tárgykód alapján történő törlésért felelős függvény
   * @param subjectCode A tárgykód
   */
  deleteBySubjectCode(subjectCode: string): Observable<any> {
    return this.api.deleteBySubjectCode(subjectCode);
  }

  /**
   * A szemeszter alapján történő törlésért felelős függvény
   * @param semester A fszemeszter
   */
  deleteBySemester(semester: string): Observable<any> {
    return this.api.deleteBySemester(semester);
  }

  /**
   * A státusz alapján történő törlésért felelős függvény
   * @param status A foglalás státusza
   */
  deleteByStatus(status: string): Observable<any> {
    return this.api.deleteByStatus(status);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }
}
