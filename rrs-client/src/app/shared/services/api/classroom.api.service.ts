import { Injectable } from "@angular/core";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { Classroom } from "../../models/Classroom";

/**
 * Az tantermekhez tartozó service osztály
 */
@Injectable()
export class ClassroomApiService {
  constructor(private http: HttpClient) {}

  /**
   * A tantermek lekérdezéséért felelős függvény
   */
  getAll(): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_GET_ALL))
    );
  }

  /**
   * Egy adott épülethez tartozó tantermek neveinek lekérdezését megvalósító függvény
   * @param building Az épület neve
   */
  getNamesByBuilding(building: string): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_GET_NAMES_BY_BUILDING) +
          `?building=${building}`
      )
    );
  }

  /**
   * Az azonosító alapján történő lekérdezést megvalósító függvény
   * @param id Az azonosító
   */
  findById(id: number): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_ID) + "/" + id
      )
    );
  }

  /**
   * Egy adott névhez tartozó tantermek lekérdezését megvalósító függvény
   * @param roomName A tanterem neve
   */
  findByName(roomName: string): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_NAME) +
          "/" +
          roomName
      )
    );
  }

  /**
   * Egy adott épülethez tartozó tantermek lekérdezését megvalósító függvény
   * @param buildingName Az épület neve
   */
  findByBuildingName(buildingName: string): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_BUILDING_NAME) +
          "/" +
          buildingName
      )
    );
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
    return <Observable<Classroom>>(
      this.http.get(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_FIND_BY_NAME_AND_BUILDING_NAME
        ) + `?name=${name}&buildingName=${buildingName}`
      )
    );
  }

  /**
   * A PC-vel rendelkező/nem rendelkező tantermek lekérdezését megvalósító függvény
   * @param hasPC Van-e PC?
   */
  findByHasPC(hasPC: boolean): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_HAS_PC) + "/" + hasPC
      )
    );
  }

  /**
   * A projektorral rendelkező/nem rendelkező tantermek lekérdezését megvalósító függvény
   * @param hasProjector Van-e projektor?
   */
  findByHasProjector(hasProjector: boolean): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_HAS_PROJECTOR) +
          "/" +
          hasProjector
      )
    );
  }

  /**
   * A megadott számnál kevesebb székkel rendelkező tantermek lekérdezését megvalósító függvény
   * @param chairs A székek száma
   */
  findByChairsLessThan(chairs: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_CHAIRS_LESS_THAN) +
          "/" +
          chairs
      )
    );
  }

  /**
   * A megadott számnál több székkel rendelkező tantermek lekérdezését megvalósító függvény
   * @param chairs A székek száma
   */
  findByChairsGreaterThan(chairs: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN
        ) +
          "/" +
          chairs
      )
    );
  }

  /**
   * A megadott számok közötti székkekel rendelkező tantermek lekérdezését megvalósító függvény
   * @param from A székek száma (alsó korlát)
   * @param to A székek száma (felső korlát)
   */
  findByChairsBetween(from: number, to: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_CHAIRS_BETWEEN) +
          `?from=${from}&to=${to}`
      )
    );
  }

  /**
   * A tanterem létrehozásáért felelős függvény
   * @param classroom A tanterem objektum
   */
  createClassroom(classroom: Classroom): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.post(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_CREATE_CLASSROOM),
        classroom
      )
    );
  }

  /**
   * Az tanterem frissítéséért felelős függvény
   * @param id Az azonosító
   * @param reservation A tanterem
   */
  update(id: number, classroom: Classroom): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.put(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_UPDATE) + `/${id}`,
        classroom
      )
    );
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
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_DELETE_BY_NAME_AND_BUILDING_NAME
        ) + `?name=${name}&buildingName=${buildingName}`
      )
    );
  }

  /**
   * Az azonosító alapján történő létezést ellenőrző függvény
   * @param id Az azonosító
   */
  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_EXISTS_BY_ID) + `?id=${id}`
      )
    );
  }

  /**
   * A név és épület alapján történő létezést ellenőrző függvény
   * @param name A tanterem neve
   * @param building Az épület neve
   */
  existsByNameAndBuilding(name: string, building: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_EXISTS_BY_NAME_AND_BUILDING
        ) + `?name=${name}&building=${building}`
      )
    );
  }
}
