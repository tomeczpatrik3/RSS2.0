import { Injectable } from '@angular/core';
import { Classroom } from '../models/Classroom';
import { Routes } from '../config/routes.config';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ClassroomService {
  constructor(
    private http: HttpClient,
  ) { }

  getAll(): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM));
  }
  
  getRoomNames(): Observable<string[]> {
    return <Observable<string[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_GET_NAMES));
  }

  getBuildings(): Observable<string[]> {
    return <Observable<string[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_GET_BUILDINGS));
  }
  
  findByBuildingName(buildingName: string): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_FIND_BY_BUILDING_NAME) + "/" + buildingName);
  }

  findByName(roomName: string): Observable<Classroom> {
    return <Observable<Classroom>>this.http.get(Routes.getUrl(Routes.CLASSROOM_FIND_BY_NAME) + "/" + roomName);
  }

  findByHasPC(hasPC: boolean): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_FIND_BY_HAS_PC) + "/" + hasPC);
  }

  findByHasProjector(hasProjector: boolean): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_FIND_BY_HAS_PROJECTOR) + "/" + hasProjector);
  }

  findByChairsLessThan(chairs: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_FIND_BY_CHAIRS_LESS_THAN) + "/" + chairs);
  }

  findByChairsGreaterThan(chairs: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.getUrl(Routes.CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN) + "/" +chairs);
  }
  /*
  findByChairsBetween(from: number, to: number): Observable<Classroom[]> {
    return <Observable<Classroom[]>>this.http.get(Routes.CLASSROOM_FIND_BY_CHAIRS_BETWEEN);
  }
  */

  deleteByRoomName(roomName: string) {
    return <Observable<Classroom[]>>this.http.post(Routes.getUrl(Routes.CLASSROOM_DELETE_BY_ROOM_NAME), roomName);
  }

  createClassroom(classroom: Classroom) {
    return <Observable<Classroom[]>>this.http.post(Routes.getUrl(Routes.CLASSROOM_CREATE_CLASSROOM),classroom);
  }
}
