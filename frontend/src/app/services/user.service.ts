import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Routes } from '../config/routes.config';
import { User } from '../models/User';
import { HttpService } from './http.service';
import { Observable } from 'rxjs/Observable';
import { AccountCredentials } from '../models/AccountCredentials';
import { AuthService } from './auth.service';
import { Config } from '../config/config';
import { Response } from '@angular/http/src/static_response';

@Injectable()
export class UserService {

  constructor(
    private http: HttpService,
    private authService: AuthService
  ) { }

  login(accountCredentials: AccountCredentials) {
    return this.http.post(Routes.USER_LOGIN, accountCredentials, false)
        .subscribe( (res: Response) => {
            this.authService.login(res.headers.get(Config.TOKEN_NAME));
        });       
  }

  getAll(): Observable<User[]> {
    return <Observable<User[]>>this.http.get(Routes.USER);
  }

  getNames(): Observable<string[]> {
    return <Observable<string[]>>this.http.get(Routes.USER_GET_NAMES);
  }

  findByName(name: string): Observable<User[]> {
    return <Observable<User[]>>this.http.get(Routes.USER_FIND_BY_NAME, name);
  } 

  findByUsername(username: string): Observable<User> {
    return <Observable<User>>this.http.get(Routes.USER_FIND_BY_USERNAME, username);
  } 

  findByRole(role: string): Observable<User[]> {
    return <Observable<User[]>>this.http.get(Routes.USER_FIND_BY_ROLE, role);
  } 

  deleteByUsername(username: string): Observable<User> {
    return <Observable<User>>this.http.post(Routes.USER_DELETE_BY_USERNAME, username);
  } 

  createUser(user: User): Observable<User> {
    return <Observable<User>>this.http.post(Routes.USER_CREATE_USER, user);
  } 
}
