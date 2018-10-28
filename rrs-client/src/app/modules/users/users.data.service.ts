import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { UserApiService } from "../../shared/services/api/user.api.service";
import { AccountCredentials } from "../../shared/models/AccountCredentials";
import { User } from "../../shared/models/User";

/**
 * Az felhasználókhoz tartozó service osztály
 */
@Injectable()
export class UsersDataService {
  constructor(private api: UserApiService) {}

  /**
   * A bejelentkezésért felelős függvény
   * @param accountCredentials A felhasználói adatok
   */
  login(accountCredentials: AccountCredentials) {
    return this.api.login(accountCredentials);
  }

  /**
   * A felhasználók lekérdezéséért felelős függvény
   */
  getAll(): Observable<User[]> {
    return this.api.getAll();
  }

  /**
   * A felhasználók neveinek lekérdezéséért felelős függvény
   */
  getNames(): Observable<string[]> {
    return this.api.getNames();
  }

  /**
   * Egy adott felhasználóhoz tartozó (teljes) név lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  getNameByUsername(username: string): Observable<any> {
    return this.api.getNameByUsername(username);
  }

  /**
   * Egy adott azonosító alapján történő lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<User> {
    return this.api.findById(id);
  }

  /**
   * Egy adott felhasználó, felhasználónév alapján történő lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  findByUsername(username: string): Observable<User> {
    return this.api.findByUsername(username);
  }

  /**
   * A felhasználók (teljes) név alapján történő lekérdezését megvalósító függvény
   * @param name A (teljes) név
   */
  findByName(name: string): Observable<User[]> {
    return this.api.findByName(name);
  }

  /**
   * A regisztrációért felelős függvény
   * @param user A felhasználó objektum a megfelelő adatokkal feltöltve
   */
  createUser(user: User): Observable<User> {
    return this.api.createUser(user);
  }

  /**
   * Az felhasználó frissítéséért felelős függvény
   * @param id Az azonosító
   * @param user A felhasználó
   */
  update(id: number, user: User): Observable<User> {
    return this.api.update(id, user);
  }

  /**
   * A felhasználónév alapján történő törlésért felelős függvény
   * @param username A felhasználónév
   */
  deleteByUsername(username: string): Observable<any> {
    return this.api.deleteByUsername(username);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }

  /**
   * A felhasználónév alapján történő létezést ellenőrző függvény
   * @param username A felhasználónév
   */
  existsByUsername(username: string): Observable<boolean> {
    return this.api.existsByUsername(username);
  }

  /**
   * Az e-mail cím alapján történő létezést ellenőrző függvény
   * @param email Az e-mail cím
   */
  existsByEmail(email: string): Observable<boolean> {
    return this.api.existsByEmail(email);
  }
}
