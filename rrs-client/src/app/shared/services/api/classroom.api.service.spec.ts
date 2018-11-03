import {
  HttpTestingController,
  HttpClientTestingModule
} from "@angular/common/http/testing";
import { TestBed, getTestBed } from "@angular/core/testing";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { ClassroomApiService } from "./classroom.api.service";
import { Classroom } from "../../models/Classroom";

describe("ClassroomApiService", () => {
  let injector;
  let service: ClassroomApiService;
  let httpMock: HttpTestingController;
  let testRooms: Classroom[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClassroomApiService]
    });

    injector = getTestBed();
    service = injector.get(ClassroomApiService);
    httpMock = injector.get(HttpTestingController);

    testRooms = [
      new Classroom("Teszt terem 1", true, true, 100, "Teszt épület 1", 1),
      new Classroom("Teszt terem 2", false, false, 50, "Teszt épület 2", 2)
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAll", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.getAll().subscribe(rooms => {
        expect(rooms.length).toBe(2);
        expect(rooms).toEqual(testRooms);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_GET_ALL)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testRooms);
    });
  });

  describe("#getNamesByBuilding", () => {
    it("a visszatérési érték: Observable<string[]>", () => {
      const testNames: string[] = ["Teszt terem 1"];

      service.getNamesByBuilding(testRooms[0].building).subscribe(names => {
        expect(names.length).toBe(1);
        expect(names).toEqual(testNames);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_GET_NAMES_BY_BUILDING) +
          `?building=${testRooms[0].building}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testNames);
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<Classroom>", () => {
      service.findById(testRooms[0].id).subscribe(room => {
        expect(room).toEqual(testRooms[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_ID) +
          "/" +
          testRooms[0].id
      );
      expect(req.request.method).toBe("GET");
      req.flush(testRooms[0]);
    });

    it("ClassroomNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tanterem";

      service.findById(testRooms[0].id).subscribe(
        room =>
          fail("ClassroomNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_ID) +
          "/" +
          testRooms[0].id
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByName", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByName(testRooms[0].name).subscribe(rooms => {
        expect(rooms.length).toBe(1);
        expect(rooms).toEqual([testRooms[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_NAME) +
          "/" +
          testRooms[0].name
      );
      expect(req.request.method).toBe("GET");
      req.flush([testRooms[0]]);
    });
  });

  describe("#findByBuildingName", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByBuildingName(testRooms[0].building).subscribe(rooms => {
        expect(rooms.length).toBe(1);
        expect(rooms).toEqual([testRooms[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_BUILDING_NAME) +
          "/" +
          testRooms[0].building
      );
      expect(req.request.method).toBe("GET");
      req.flush([testRooms[0]]);
    });
  });

  describe("#findByNameAndBuildingName", () => {
    it("a visszatérési érték: Observable<Classroom>", () => {
      service
        .findByNameAndBuildingName(testRooms[0].name, testRooms[0].building)
        .subscribe(room => {
          expect(room).toEqual(testRooms[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_FIND_BY_NAME_AND_BUILDING_NAME
        ) + `?name=${testRooms[0].name}&buildingName=${testRooms[0].building}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testRooms[0]);
    });

    it("ClassroomNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tanterem";

      service
        .findByNameAndBuildingName(testRooms[0].name, testRooms[0].building)
        .subscribe(
          room =>
            fail("ClassroomNotExistsException kellett volna hogy történjen!"),
          (error: HttpErrorResponse) => {
            expect(error.status).toEqual(404, "status");
            expect(error.error).toEqual(errorMsg, "message");
          }
        );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_FIND_BY_NAME_AND_BUILDING_NAME
        ) + `?name=${testRooms[0].name}&buildingName=${testRooms[0].building}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByHasPC", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByHasPC(true).subscribe(rooms => {
        expect(rooms.length).toBe(1);
        expect(rooms).toEqual([testRooms[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_HAS_PC) + "/" + true
      );
      expect(req.request.method).toBe("GET");
      req.flush([testRooms[0]]);
    });
  });

  describe("#findByHasProjector", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByHasProjector(true).subscribe(rooms => {
        expect(rooms.length).toBe(1);
        expect(rooms).toEqual([testRooms[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_HAS_PROJECTOR) +
          "/" +
          true
      );
      expect(req.request.method).toBe("GET");
      req.flush([testRooms[0]]);
    });
  });

  describe("#findByChairsLessThan", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByChairsLessThan(70).subscribe(rooms => {
        expect(rooms.length).toBe(1);
        expect(rooms).toEqual([testRooms[1]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_CHAIRS_LESS_THAN) +
          "/" +
          70
      );
      expect(req.request.method).toBe("GET");
      req.flush([testRooms[1]]);
    });
  });

  describe("#findByChairsGreaterThan", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByChairsGreaterThan(70).subscribe(rooms => {
        expect(rooms.length).toBe(1);
        expect(rooms).toEqual([testRooms[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_FIND_BY_CHAIRS_GREATER_THAN
        ) +
          "/" +
          70
      );
      expect(req.request.method).toBe("GET");
      req.flush([testRooms[0]]);
    });
  });

  describe("#findByChairsBetween", () => {
    it("a visszatérési érték: Observable<Classroom[]>", () => {
      service.findByChairsBetween(20, 150).subscribe(rooms => {
        expect(rooms.length).toBe(2);
        expect(rooms).toEqual(testRooms);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_FIND_BY_CHAIRS_BETWEEN) +
          `?from=${20}&to=${150}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testRooms);
    });
  });

  describe("#createClassroom", () => {
    it("a visszatérési érték: Observable<Classroom>", () => {
      service.createClassroom(testRooms[0]).subscribe(room => {
        expect(room).toEqual(testRooms[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_CREATE_CLASSROOM)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testRooms[0]);
    });

    it("ClassroomAlredyExistsException", () => {
      const errorMsg = "Már létezik ez a tanterem";

      service.createClassroom(testRooms[0]).subscribe(
        user =>
          fail("ClassroomAlredyExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_CREATE_CLASSROOM)
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<Classroom>", () => {
      service.update(testRooms[0].id, testRooms[0]).subscribe(room => {
        expect(room).toEqual(testRooms[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_UPDATE) +
          `/${testRooms[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testRooms[0]);
    });

    it("ClassroomNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tanterem";

      service.update(testRooms[0].id, testRooms[0]).subscribe(
        room =>
          fail("ClassroomNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_UPDATE) +
          `/${testRooms[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#existsById", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsById(testRooms[0].id).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_EXISTS_BY_ID) +
          `?id=${testRooms[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsById(testRooms[0].id).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASSROOM_EXISTS_BY_ID) +
          `?id=${testRooms[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });

  describe("#existsByNameAndBuilding", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service
        .existsByNameAndBuilding(testRooms[0].name, testRooms[0].building)
        .subscribe(result => {
          expect(result).toEqual(true);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_EXISTS_BY_NAME_AND_BUILDING
        ) + `?name=${testRooms[0].name}&building=${testRooms[0].building}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service
        .existsByNameAndBuilding(testRooms[0].name, testRooms[0].building)
        .subscribe(result => {
          expect(result).toEqual(false);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASSROOM_EXISTS_BY_NAME_AND_BUILDING
        ) + `?name=${testRooms[0].name}&building=${testRooms[0].building}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
