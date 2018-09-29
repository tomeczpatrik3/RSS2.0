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

  login(accountCredentials: AccountCredentials) {
    return this.http.post(
      Routes.getUrl(Routes.USER_LOGIN),
      accountCredentials,
      { observe: "response" }
    );
  }

  getAll(): Observable<User[]> {
    return <Observable<User[]>>(
      this.http.get(Routes.getUrl(Routes.USER_GET_ALL))
    );
  }

  getNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.USER_GET_NAMES))
    );
  }

  findByUsername(username: string): Observable<User> {
    return <Observable<User>>(
      this.http.get(
        Routes.getUrl(Routes.USER_FIND_BY_USERNAME) + `?username=${username}`
      )
    );
  }

  findByName(name: string): Observable<User[]> {
    return <Observable<User[]>>(
      this.http.get(Routes.getUrl(Routes.USER_FIND_BY_NAME) + `?name=${name}`)
    );
  }

  createUser(user: User): Observable<User> {
    return <Observable<User>>(
      this.http.post(Routes.getUrl(Routes.USER_CREATE_USER), user)
    );
  }

  deleteByUsername(username: string): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        Routes.getUrl(Routes.USER_DELETE_BY_USERNAME) + `?username=${username}`
      )
    );
  }
}
