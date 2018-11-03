import {
  HttpTestingController,
  HttpClientTestingModule
} from "@angular/common/http/testing";
import { TestBed, getTestBed } from "@angular/core/testing";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { SubjectApiService } from "./subject.api.service";
import { Subject } from "../../models/Subject";

describe("SubjectApiService", () => {
  let injector;
  let service: SubjectApiService;
  let httpMock: HttpTestingController;
  let testSubjects: Subject[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SubjectApiService]
    });

    injector = getTestBed();
    service = injector.get(SubjectApiService);
    httpMock = injector.get(HttpTestingController);

    testSubjects = [
      new Subject("Teszt 1", "test001", 1),
      new Subject("Teszt 2", "test002", 2)
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAll", () => {
    it("a visszatérési érték: Observable<Subject[]>", () => {
      service.getAll().subscribe(subjects => {
        expect(subjects.length).toBe(2);
        expect(subjects).toEqual(testSubjects);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_GET_ALL)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testSubjects);
    });
  });

  describe("#getSubjectNames", () => {
    it("a visszatérési érték: Observable<string[]>", () => {
      const testNames: string[] = ["Teszt 1", "Teszt 2"];

      service.getSubjectNames().subscribe(names => {
        expect(names.length).toBe(2);
        expect(names).toEqual(testNames);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_GET_SUBJECT_NAMES)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testNames);
    });
  });

  describe("#getSubjectName", () => {
    it("a visszatérési érték: Observable<any>", () => {
      service.getSubjectName(testSubjects[0].code).subscribe(name => {
        expect(name).toEqual(testSubjects[0].name);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_GET_SUBJECT_NAME) +
          `?subjectCode=${testSubjects[0].code}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testSubjects[0].name);
    });

    it("SubjectNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tantárgy";

      service.getSubjectName(testSubjects[0].code).subscribe(
        name => fail("SubjectNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_GET_SUBJECT_NAME) +
          `?subjectCode=${testSubjects[0].code}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<Subject>", () => {
      service.findById(testSubjects[0].id).subscribe(subject => {
        expect(subject).toEqual(testSubjects[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_FIND_BY_ID) +
          `/${testSubjects[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testSubjects[0]);
    });

    it("SubjectNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tantárgy";

      service.findById(testSubjects[0].id).subscribe(
        subject =>
          fail("SubjectNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_FIND_BY_ID) +
          `/${testSubjects[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#createSubject", () => {
    it("a visszatérési érték: Observable<Subject>", () => {
      service.createSubject(testSubjects[0]).subscribe(subject => {
        expect(subject).toEqual(testSubjects[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_CREATE_SUBJECT)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testSubjects[0]);
    });

    it("SubjectAlredyExistsException", () => {
      const errorMsg = "Ilyen tantárgy már létezik";

      service.createSubject(testSubjects[0]).subscribe(
        subject =>
          fail("SubjectAlredyExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_CREATE_SUBJECT)
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<Subject>", () => {
      service.update(testSubjects[0].id, testSubjects[0]).subscribe(subject => {
        expect(subject).toEqual(testSubjects[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_UPDATE) +
          `/${testSubjects[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testSubjects[0]);
    });

    it("SubjectNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen tantárgy";

      service.update(testSubjects[0].id, testSubjects[0]).subscribe(
        subject =>
          fail("SubjectNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_UPDATE) +
          `/${testSubjects[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#existsById", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsById(testSubjects[0].id).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_EXISTS_BY_ID) +
          `?id=${testSubjects[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsById(testSubjects[0].id).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_EXISTS_BY_ID) +
          `?id=${testSubjects[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });

  describe("#existsByCode", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsByCode(testSubjects[0].code).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_EXISTS_BY_CODE) +
          `?code=${testSubjects[0].code}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsByCode(testSubjects[0].code).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.SUBJECT_EXISTS_BY_CODE) +
          `?code=${testSubjects[0].code}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
