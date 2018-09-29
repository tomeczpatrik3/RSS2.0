import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Routes } from '../config/routes.config';

/**
 * A Http kérésekkel foglaljouó service osztály
 */
@Injectable()
export class HttpService {

  constructor(
    private http: HttpClient
  ) {}

  /**
   * A megfelelő get metódus generálását végző függvény
   * @param route Az útvonal
   * @param params A paraméterek (ha van)
   */
  get(route: string, params?: any) {
    return this.http.get(
      Routes.getUrl(route),
      params ? "" : params
    );
  }

  /**
   * A megfelelő post metódus generálását végző függvény
   * @param route Az útvonal
   * @param body Az üzenettest
   */
  post(route: string, body: any) {
    return this.http.post(
      Routes.getUrl(route),
      body
    );
  }

}
