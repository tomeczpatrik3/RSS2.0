import { TestBed, getTestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController
} from "@angular/common/http/testing";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
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

  afterEach(() => {
    httpMock.verify();
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

    it("BuildingNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen azonosítóval rendelkező épület";

      service.findById(1).subscribe(
        building =>
          fail("BuildingNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_FIND_BY_ID) + "/1"
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
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

    it("BuildingNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen nével rendelkező épület";

      service.findByName("TESZT_1").subscribe(
        building =>
          fail("BuildingNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_FIND_BY_NAME) + "/TESZT_1"
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
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

    it("BuildingAlredyExistsException", () => {
      const testBuilding: Building = new Building("TESZT1");
      const errorMsg = "Már létezik ilyen nével rendelkező épület";

      service.createBuilding(testBuilding).subscribe(
        building =>
          fail("BuildingAlredyExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_CREATE_BUILDING)
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<Building>", () => {
      let testBuilding: Building = new Building("TESZT1");
      testBuilding.id = 1;

      service.update(1, testBuilding).subscribe(building => {
        expect(building).toEqual(testBuilding);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_UPDATE) + `/1`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testBuilding);
    });

    it("BuildingNotExistsException", () => {
      let testBuilding: Building = new Building("TESZT1");
      testBuilding.id = 1;
      const errorMsg = "Nem létezik ilyen azonosítóval rendelkező épület";

      service.update(1, testBuilding).subscribe(
        building =>
          fail("BuildingNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_UPDATE) + `/1`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#existsById", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      const testValue: boolean = true;
      const id: number = 1;

      service.existsById(id).subscribe(result => {
        expect(result).toEqual(testValue);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_EXISTS_BY_ID) + `?id=${id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: testValue }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      const testValue: boolean = false;
      const id: number = 1;

      service.existsById(id).subscribe(result => {
        expect(result).toEqual(testValue);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_EXISTS_BY_ID) + `?id=${id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: testValue }));
    });
  });

  describe("#existsByName", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      const name: string = "Teszt épület";

      service.existsByName(name).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_EXISTS_BY_NAME) +
          `?name=${name}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      const name: string = "Teszt épület";

      service.existsByName(name).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.BUILDING_EXISTS_BY_NAME) +
          `?name=${name}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
