import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { ClassroomApiService } from "../../shared/services/api/classroom.api.service";
import { Classroom } from "../../shared/models/Classroom";

/**
 * Az tantermekhez tartozó service osztály
 */
@Injectable()
export class ClassroomsDataService {
  constructor(private api: ClassroomApiService) {}

  /**
   * A tantermek lekérdezéséért felelős függvény
   */
  getAll(): Observable<Classroom[]> {
    return this.api.getAll();
  }

  /**
   * Egy adott épülethez tartozó tantermek neveinek lekérdezését megvalósító függvény
   * @param building Az épület neve
   */
  getNamesByBuilding(building: string): Observable<string[]> {
    return this.api.getNamesByBuilding(building);
  }

  /**
   * Egy adott névhez tartozó tantermek lekérdezését megvalósító függvény
   * @param roomName A tanterem neve
   */
  findByName(roomName: string): Observable<Classroom> {
    return this.api.findByName(roomName);
  }

  /**
   * Egy adott épülethez tartozó tantermek lekérdezését megvalósító függvény
   * @param buildingName Az épület neve
   */
  findByBuildingName(buildingName: string): Observable<Classroom[]> {
    return this.api.findByBuildingName(buildingName);
  }

  /**
   * Egy adott névhez és épülethez tartozó tanterem lekérdezését megvalósító függvény
   * @param name A tanterem neve
   * @param buildingName Az épület neve
   */
  findByNameAndBuildingName(
    name: string,
    buildingName: string
  ): Observable<Classroom> {
    return this.api.findByNameAndBuildingName(name, buildingName);
  }

  /**
   * A PC-vel rendelkező/nem rendelkező tantermek lekérdezését megvalósító függvény
   * @param hasPC Van-e PC?
   */
  findByHasPC(hasPC: boolean): Observable<Classroom[]> {
    return this.api.findByHasPC(hasPC);
  }

  /**
   * A projektorral rendelkező/nem rendelkező tantermek lekérdezését megvalósító függvény
   * @param hasProjector Van-e projektor?
   */
  findByHasProjector(hasProjector: boolean): Observable<Classroom[]> {
    return this.api.findByHasProjector(hasProjector);
  }

  /**
   * A megadott számnál kevesebb székkel rendelkező tantermek lekérdezését megvalósító függvény
   * @param chairs A székek száma
   */
  findByChairsLessThan(chairs: number): Observable<Classroom[]> {
    return this.api.findByChairsLessThan(chairs);
  }

  /**
   * A megadott számnál több székkel rendelkező tantermek lekérdezését megvalósító függvény
   * @param chairs A székek száma
   */
  findByChairsGreaterThan(chairs: number): Observable<Classroom[]> {
    return this.api.findByChairsGreaterThan(chairs);
  }

  /**
   * A megadott számok közötti székkekel rendelkező tantermek lekérdezését megvalósító függvény
   * @param from A székek száma (alsó korlát)
   * @param to A székek száma (felső korlát)
   */
  findByChairsBetween(from: number, to: number): Observable<Classroom[]> {
    return this.api.findByChairsBetween(from, to);
  }

  /**
   * A tanterem létrehozásáért felelős függvény
   * @param classroom A tanterem objektum
   */
  createClassroom(classroom: Classroom): Observable<Classroom> {
    return this.api.createClassroom(classroom);
  }

  /**
   * Egy adott névhez és épülethez tartozó tanterem törléséért felelős függvény
   * @param name A tanterem neve
   * @param buildingName Az épület neve
   */
  deleteByNameAndBuildingName(
    name: string,
    buildingName: string
  ): Observable<any> {
    return this.api.deleteByNameAndBuildingName(name, buildingName);
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return this.api.existsById(id);
  }

  /**
   * A név és épület alapján történő létezést ellenőrző függvény
   * @param name A tanterem neve
   * @param building Az épület neve
   */
  existsByNameAndBuilding(name: string, building: string): Observable<boolean> {
    return this.api.existsByNameAndBuilding(name, building);
  }
}
