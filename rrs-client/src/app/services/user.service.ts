import { Injectable } from "@angular/core";
import { Routes } from "../config/routes.config";
import { User } from "../models/User";
import { Observable } from "rxjs/Observable";
import { AccountCredentials } from "../models/AccountCredentials";
import { HttpClient } from "@angular/common/http";

/**
 * Az felhasználókhoz tartozó service osztály
 */
@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}

  /**
   * A bejelentkezésért felelős függvény
   * @param accountCredentials A felhasználói adatok
   */
  login(accountCredentials: AccountCredentials) {
    return this.http.post(
      Routes.getUrl(Routes.USER_LOGIN),
      accountCredentials,
      { observe: "response" }
    );
  }

  /**
   * A felhasználók lekérdezéséért felelős függvény
   */
  getAll(): Observable<User[]> {
    return <Observable<User[]>>(
      this.http.get(Routes.getUrl(Routes.USER_GET_ALL))
    );
  }

  /**
   * A felhasználók neveinek lekérdezéséért felelős függvény
   */
  getNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.USER_GET_NAMES))
    );
  }

  /**
   * Egy adott felhasználóhoz tartozó (teljes) név lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  getNameByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.USER_GET_NAME_BY_USERNAME) +
          `?username=${username}`
      )
    );
  }

  /**
   * Egy adott felhasználó, felhasználónév alapján történő lekérdezését megvalósító függvény
   * @param username A felhasználónév
   */
  findByUsername(username: string): Observable<User> {
    return <Observable<User>>(
      this.http.get(
        Routes.getUrl(Routes.USER_FIND_BY_USERNAME) + `?username=${username}`
      )
    );
  }

  /**
   * A felhasználók (teljes) név alapján történő lekérdezését megvalósító függvény
   * @param name A (teljes) név
   */
  findByName(name: string): Observable<User[]> {
    return <Observable<User[]>>(
      this.http.get(Routes.getUrl(Routes.USER_FIND_BY_NAME) + `?name=${name}`)
    );
  }

  /**
   * A regisztrációért felelős függvény
   * @param user A felhasználó objektum a megfelelő adatokkal feltöltve
   */
  createUser(user: User): Observable<User> {
    return <Observable<User>>(
      this.http.post(Routes.getUrl(Routes.USER_CREATE_USER), user)
    );
  }

  /**
   * A felhasználónév alapján történő törlésért felelős függvény
   * @param username A felhasználónév
   */
  deleteByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.USER_DELETE_BY_USERNAME) + `?username=${username}`
      )
    );
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(Routes.getUrl(Routes.USER_EXISTS_BY_ID) + `?id=${id}`)
    );
  }

  /**
   * A felhasználónév alapján történő létezést ellenőrző függvény
   * @param username A felhasználónév
   */
  existsByUsername(username: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.USER_EXISTS_BY_USERNAME) + `?username=${username}`
      )
    );
  }

  /**
   * Az e-mail cím alapján történő létezést ellenőrző függvény
   * @param email Az e-mail cím
   */
  existsByEmail(email: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.USER_EXISTS_BY_EMAIL) + `?email=${email}`
      )
    );
  }
}
