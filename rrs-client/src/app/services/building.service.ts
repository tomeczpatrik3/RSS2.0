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

  /**
   * Az épületek lekérdezéséért felelős függvény
   */
  getAll(): Observable<Building[]> {
    return <Observable<Building[]>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_GET_ALL))
    );
  }

  /**
   * Az épületek neveinek lekérdezéséért felelős függvény
   */
  getNames(): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_GET_NAMES))
    );
  }

  /**
   * Az azonosító alapján történő lekérdezést megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<Building> {
    return <Observable<Building>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_FIND_BY_ID) + "/" + id)
    );
  }

  /**
   * A név alapján történő lekérdezést megvalósító függvény
   * @param name Az épület neve
   */
  findByName(name: string): Observable<Building> {
    return <Observable<Building>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_FIND_BY_NAME) + "/" + name)
    );
  }

  /**
   * Az épület létrehozásáért felelős függvény
   * @param building Az épület objektum
   */
  createBuilding(building: Building): Observable<Building> {
    return <Observable<Building>>(
      this.http.post(Routes.getUrl(Routes.BUILDING_CREATE_BUILDING), building)
    );
  }

  /**
   * A név alapján történő törlésért felelős függvény
   * @param name Az épület neve
   */
  deleteByName(name: string): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.BUILDING_DELETE_BY_NAME) + "/" + name
      )
    );
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(Routes.getUrl(Routes.BUILDING_EXISTS_BY_ID) + `?id=${id}`)
    );
  }

  /**
   * A név alapján történő létezést ellenőrző függvény
   * @param name Az épület neve
   */
  existsByName(name: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.BUILDING_EXISTS_BY_NAME) + `?name=${name}`
      )
    );
  }
}
