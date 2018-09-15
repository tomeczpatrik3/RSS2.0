import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Routes } from '../config/routes.config';

@Injectable()
export class HttpService {

  constructor(
    private http: HttpClient
  ) {}

  get(route: string, params?: any) {
    return this.http.get(
      Routes.getUrl(route),
      params ? "" : params
    );
  }

  post(route: string, body: any) {
    return this.http.post(
      Routes.getUrl(route),
      body
    );
  }

}
