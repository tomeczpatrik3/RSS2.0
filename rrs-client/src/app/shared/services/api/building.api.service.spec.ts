import { TestBed, getTestBed, inject } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController
} from "@angular/common/http/testing";
import { HttpParams } from "@angular/common/http";
import { BuildingApiService } from "./building.api.service";
import { Building } from "../../models/Building";
import { ApiEndpoints } from "../../config/api-endpoints.config";

describe("BuildingApiService", () => {
  let injector;
  let service: BuildingApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BuildingApiService]
    });

    injector = getTestBed();
    service = injector.get(BuildingApiService);
    httpMock = injector.get(HttpTestingController);
  });

  describe("#getAll", () => {
    it("should return an Observable<Building[]>", () => {
      const testBuildings: Building[] = [
        new Building("TESZT_1"),
        new Building("TESZT_2")
      ];

      service.getAll().subscribe(buildings => {
        expect(buildings.length).toBe(2);
        expect(buildings).toEqual(testBuildings);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_GET_ALL)
      );

      expect(req.request.method).toBe("GET");
      
      req.flush(testBuildings);

      httpMock.verify();
    });
  });


});
