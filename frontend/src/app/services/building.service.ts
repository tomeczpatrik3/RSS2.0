import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Routes } from "../config/routes.config";
import { Building } from "../models/Building";

/**
 * Az épületekhez tartozó service osztály
 */
@Injectable()
export class BuildingService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Building[]> {
    return <Observable<Building[]>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_GET_ALL))
    );
  }

  getNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_GET_NAMES))
    );
  }

  findById(id: number): Observable<Building> {
    return <Observable<Building>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_FIND_BY_ID) + "/" + id)
    );
  }

  findByName(name: string): Observable<Building> {
    return <Observable<Building>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_FIND_BY_NAME) + "/" + name)
    );
  }

  createBuilding(building: Building): Observable<Building> {
    return <Observable<Building>>(
      this.http.post(Routes.getUrl(Routes.BUILDING_CREATE_BUILDING), building)
    );
  }

  deleteByName(name: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(Routes.getUrl(Routes.BUILDING_DELETE_BY_NAME) + "/" + name)
    );
  }
}
