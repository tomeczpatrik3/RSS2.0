import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Type } from "../models/Type";
import { Routes } from "../config/routes.config";

@Injectable()
export class TypeService {

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Type[]> {
    return <Observable<Type[]>>this.http.get(Routes.getUrl(Routes.TYPE));
  }
}