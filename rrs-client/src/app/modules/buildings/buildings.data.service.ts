import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { BuildingApiService } from "../../shared/services/api/building.api.service";
import { Building } from "../../shared/models/Building";
import "rxjs/add/operator/catch";

@Injectable()
export class BuildingsDataService {
  constructor(private api: BuildingApiService) {}

  /**
   * Az épületek lekérdezéséért felelős függvény
   */
  getAll(): Observable<Building[]> {
    return this.api.getAll();
  }

  /**
   * Az épületek neveinek lekérdezéséért felelős függvény
   */
  getNames(): Observable<string[]> {
    return this.api.getNames();
  }

  /**
   * Az azonosító alapján történő lekérdezést megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<Building> {
    return this.api.findById(id);
  }

  /**
   * A név alapján történő lekérdezést megvalósító függvény
   * @param name Az épület neve
   */
  findByName(name: string): Observable<Building> {
    return this.api.findByName(name);
  }

  /**
   * Az épület létrehozásáért felelős függvény
   * @param building Az épület objektum
   */
  createBuilding(building: Building): Observable<Building> {
    return this.api.createBuilding(building);
  }

  /**
   * Az épület frissítéséért felelős függvény
   * @param id Az azonosító
   * @param building Az épület
   */
  update(id: number, building: Building): Observable<Building> {
    return this.api.update(id, building);
  }

  /**
   * A név alapján történő törlésért felelős függvény
   * @param name Az épület neve
   */
  deleteByName(name: string): Observable<any> {
    return this.api.deleteByName(name);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }

  /**
   * A név alapján történő létezést ellenőrző függvény
   * @param name Az épület neve
   */
  existsByName(name: string): Observable<boolean> {
    return this.api.existsByName(name);
  }
}
