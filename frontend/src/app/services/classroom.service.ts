import { Injectable } from "@angular/core";
import { Routes } from "../config/routes.config";
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import { Classroom } from "../models/Classroom";

/**
 * Az tantermekhez tartozó service osztály
 */
@Injectable()
export class ClassroomService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(Routes.getUrl(Routes.CLASSROOM_GET_ALL))
    );
  }

  getNamesByBuilding(building: string): Observable<string[]> {
    return <Observable<string[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_GET_NAMES_BY_BUILDING) +
          `?building=${building}`
      )
    );
  }

  findByName(roomName: string): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_NAME) + "/" + roomName
      )
    );
  }

  findByBuildingName(buildingName: string): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_BUILDING_NAME) +
          "/" +
          buildingName
      )
    );
  }

  findByNameAndBuildingName(
    name: string,
    buildingName: string
  ): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_NAME_AND_BUILDING_NAME) +
          `?name=${name}&buildingName=${buildingName}`
      )
    );
  }

  findByHasPC(hasPC: boolean): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_HAS_PC) + "/" + hasPC
      )
    );
  }

  findByHasProjector(hasProjector: boolean): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_HAS_PROJECTOR) +
          "/" +
          hasProjector
      )
    );
  }

  findByChairsLessThan(chairs: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_CHAIRS_LESS_THAN) + "/" + chairs
      )
    );
  }

  findByChairsGreaterThan(chairs: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN) +
          "/" +
          chairs
      )
    );
  }

  findByChairsBetween(from: number, to: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_FIND_BY_CHAIRS_BETWEEN) +
          `?from=${from}&to=${to}`
      )
    );
  }

  createClassroom(classroom: Classroom): Observable<Classroom> {
    return <Observable<Classroom>>(
      this.http.post(
        Routes.getUrl(Routes.CLASSROOM_CREATE_CLASSROOM),
        classroom
      )
    );
  }

  deleteByNameAndBuildingName(
    name: string,
    buildingName: string
  ): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        Routes.getUrl(Routes.CLASSROOM_DELETE_BY_NAME_AND_BUILDING_NAME) +
          `?name=${name}&buildingName=${buildingName}`
      )
    );
  }

  existsById(id: number): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(Routes.getUrl(Routes.CLASSROOM_EXISTS_BY_ID) + `?id=${id}`)
    );
  }

  existsByNameAndBuilding(name: string, building: string): Observable<boolean> {
    return <Observable<boolean>>(
      this.http.get(
        Routes.getUrl(Routes.CLASSROOM_EXISTS_BY_NAME_AND_BUILDING) +
          `?name=${name}&building=${building}`
      )
    );
  }
}
