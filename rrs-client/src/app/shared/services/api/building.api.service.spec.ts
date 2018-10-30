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
    it("a visszatérési érték: Observable<Building[]>", () => {
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
    });
  });

  describe("#getNames", () => {
    it("a visszatérési érték: Observable<string[]>", () => {
      const testNames: string[] = ["TESZT_1", "TESZT_2"];

      service.getNames().subscribe(names => {
        expect(names.length).toBe(2);
        expect(names).toEqual(testNames);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_GET_NAMES)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testNames);
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<Building>", () => {
      let testBuilding: Building = new Building("TESZT1");
      testBuilding.id = 1;

      service.findById(1).subscribe(building => {
        expect(building).toEqual(testBuilding);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_FIND_BY_ID) + "/1"
      );
      expect(req.request.method).toBe("GET");
      req.flush(testBuilding);
    });
  });

  describe("#findByName", () => {
    it("a visszatérési érték: Observable<Building>", () => {
      const testBuilding: Building = new Building("TESZT_1");

      service.findByName("TESZT_1").subscribe(buildings => {
        expect(buildings).toEqual(testBuilding);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_FIND_BY_NAME) + "/TESZT_1"
      );
      expect(req.request.method).toBe("GET");
      req.flush(testBuilding);
    });
  });

  describe("#createBuilding", () => {
    it("a visszatérési érték: Observable<Building>", () => {
      const testBuilding: Building = new Building("TESZT1");

      service.createBuilding(testBuilding).subscribe(building => {
        expect(building).toEqual(testBuilding);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_CREATE_BUILDING)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testBuilding);
    });
  });
});
