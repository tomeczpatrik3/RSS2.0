import {
  HttpTestingController,
  HttpClientTestingModule
} from "@angular/common/http/testing";
import { TestBed, getTestBed } from "@angular/core/testing";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { SemesterApiService } from "./semester.api.service";
import { Semester } from "../../models/Semester";

describe("SemesterApiService", () => {
  let injector;
  let service: SemesterApiService;
  let httpMock: HttpTestingController;
  let testSemesters: Semester[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SemesterApiService]
    });

    injector = getTestBed();
    service = injector.get(SemesterApiService);
    httpMock = injector.get(HttpTestingController);

    testSemesters = [
      new Semester("2011-2012/1", "2011.09.03", "2012.02.03", false, 1),
      new Semester("2011-2012/2", "2012.02.03", "2012.09.03", true, 2)
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAll", () => {
    it("a visszatérési érték: Observable<Semester[]>", () => {
      service.getAll().subscribe(semesters => {
        expect(semesters.length).toBe(2);
        expect(semesters).toEqual(testSemesters);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_GET_ALL)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testSemesters);
    });
  });

  describe("#getOpened", () => {
    it("a visszatérési érték: Observable<Semester[]>", () => {
      service.getOpened().subscribe(semesters => {
        expect(semesters.length).toBe(1);
        expect(semesters).toEqual([testSemesters[1]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_GET_OPENED)
      );
      expect(req.request.method).toBe("GET");
      req.flush([testSemesters[1]]);
    });
  });

  describe("#getOpenedSemesterNames", () => {
    it("a visszatérési érték: Observable<string[]>", () => {
      const testNames: string[] = ["2011-2012/2"];

      service.getOpenedSemesterNames().subscribe(names => {
        expect(names.length).toBe(1);
        expect(names).toEqual(testNames);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_GET_OPENED_NAMES)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testNames);
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<Semester>", () => {
      service.findById(testSemesters[0].id).subscribe(semester => {
        expect(semester).toEqual(testSemesters[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_FIND_BY_ID) +
          `/${testSemesters[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testSemesters[0]);
    });

    it("SemesterNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tantárgy";

      service.findById(testSemesters[0].id).subscribe(
        semester =>
          fail("SemesterNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_FIND_BY_ID) +
          `/${testSemesters[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByName", () => {
    it("a visszatérési érték: Observable<Semester>", () => {
      service.findByName(testSemesters[0].name).subscribe(semester => {
        expect(semester).toEqual(testSemesters[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_FIND_BY_NAME) +
          `?name=${testSemesters[0].name}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testSemesters[0]);
    });

    it("SemesterNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tantárgy";

      service.findByName(testSemesters[0].name).subscribe(
        semester =>
          fail("SemesterNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_FIND_BY_NAME) +
          `?name=${testSemesters[0].name}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#createSemester", () => {
    it("a visszatérési érték: Observable<Semester>", () => {
      service.createSemester(testSemesters[0]).subscribe(semester => {
        expect(semester).toEqual(testSemesters[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_CREATE_SEMESTER)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testSemesters[0]);
    });

    it("SemesterAlredyExistsException", () => {
      const errorMsg = "Ilyen szemeszter már létezik";

      service.createSemester(testSemesters[0]).subscribe(
        semester =>
          fail("SemesterAlredyExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_CREATE_SEMESTER)
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<Semester>", () => {
      service
        .update(testSemesters[0].id, testSemesters[0])
        .subscribe(semester => {
          expect(semester).toEqual(testSemesters[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_UPDATE) +
          `/${testSemesters[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testSemesters[0]);
    });

    it("SemesterNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen szemeszter";

      service.update(testSemesters[0].id, testSemesters[0]).subscribe(
        semester =>
          fail("SemesterNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_UPDATE) +
          `/${testSemesters[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#existsById", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsById(testSemesters[0].id).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_EXISTS_BY_ID) +
          `?id=${testSemesters[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsById(testSemesters[0].id).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_EXISTS_BY_ID) +
          `?id=${testSemesters[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });

  describe("#existsByName", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsByName(testSemesters[0].name).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_EXISTS_BY_NAME) +
          `?name=${testSemesters[0].name}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsByName(testSemesters[0].name).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SEMESTER_EXISTS_BY_NAME) +
          `?name=${testSemesters[0].name}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
