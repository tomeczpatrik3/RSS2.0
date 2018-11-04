import { TestBed, getTestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController
} from "@angular/common/http/testing";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { ClassReservationApiService } from "./class-reservation.api.service";
import { ClassReservation } from "../../models/ClassReservation";

describe("ClassReservationApiService", () => {
  let injector;
  let service: ClassReservationApiService;
  let httpMock: HttpTestingController;
  let testReservations: ClassReservation[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClassReservationApiService]
    });

    injector = getTestBed();
    service = injector.get(ClassReservationApiService);
    httpMock = injector.get(HttpTestingController);

    testReservations = [
      new ClassReservation(
        "testJani",
        "Teszt épület 1",
        "Teszt tanterem 1",
        "ACCEPTED",
        "Ez egy megjegyzés",
        "2011-2012/1",
        "IKP001",
        ["2011.10.05 10:30"],
        ["2011.10.05 12:30"],
        1
      ),
      new ClassReservation(
        "tesztGabi",
        "Teszt épület 2",
        "Teszt tanterem 2",
        "PENDING",
        "Ez egy megjegyzés",
        "2011-2012/2",
        "IKP002",
        ["2012.05.05 10:30"],
        ["2011.05.05 12:30"],
        2
      ),
      new ClassReservation(
        "tesztGabi",
        "Teszt épület 2",
        "Teszt tanterem 2",
        "DECLINED",
        "Ez egy megjegyzés",
        "2011-2012/2",
        "IKP002",
        ["2012.05.05 10:30"],
        ["2011.05.05 12:30"],
        3
      )
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAccepted", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service.getAccepted().subscribe(reservations => {
        expect(reservations.length).toBe(1);
        expect(reservations).toEqual([testReservations[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_GET_ACCEPTED)
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#getPending", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service.getPending().subscribe(reservations => {
        expect(reservations.length).toBe(1);
        expect(reservations).toEqual([testReservations[1]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_GET_PENDING)
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[1]]);
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service.findById(testReservations[0].id).subscribe(reservation => {
        expect(reservation).toEqual(testReservations[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_FIND_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testReservations[0]);
    });

    it("ClassReservationNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen foglalás";

      service.findById(testReservations[0].id).subscribe(
        reservation =>
          fail(
            "ClassReservationNotExistsException kellett volna hogy történjen!"
          ),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_FIND_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByUsername", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service
        .findByUsername(testReservations[0].username)
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_FIND_BY_USERNAME) +
          "/" +
          testReservations[0].username
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findByStatus", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service
        .findByStatus(testReservations[0].status)
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASS_RESERVATION_FIND_BY_STATUS +
            "/" +
            testReservations[0].status
        )
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findByBuildingAndClassroom", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service
        .findByBuildingAndClassroom(
          testReservations[0].building,
          testReservations[0].classroom
        )
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASS_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM
        ) +
          `?building=${testReservations[0].building}&classroom=${
            testReservations[0].classroom
          }`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findBySubjectCode", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service
        .findBySubjectCode(testReservations[0].subjectCode)
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.CLASS_RESERVATION_FIND_BY_SUBJECT_CODE
        ) + `?subjectCode=${testReservations[0].subjectCode}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findBySemester", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      service
        .findBySemester(testReservations[0].semester)
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_FIND_BY_SEMESTER) +
          `?semester=${testReservations[0].semester}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#setStatus", () => {
    it("a visszatérési érték: Observable<ClassReservation>", () => {
      let testRes = testReservations[0];
      testRes.status = "DECLINED";

      service
        .setStatus(testReservations[0].id, "DECLINED")
        .subscribe(reservation => {
          expect(reservation).toEqual(testRes);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_SET_STATUS) +
          `?id=${testReservations[0].id}&status=${"DECLINED"}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testRes);
    });
  });

  it("ClassReservationNotExistsException", () => {
    const errorMsg = "Nem létezik ilyen foglalás";

    service.setStatus(testReservations[0].id, "DECLINED").subscribe(
      reservation =>
        fail(
          "ClassReservationNotExistsException kellett volna hogy történjen!"
        ),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, "status");
        expect(error.error).toEqual(errorMsg, "message");
      }
    );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_SET_STATUS) +
        `?id=${testReservations[0].id}&status=${"DECLINED"}`
    );
    req.flush(errorMsg, { status: 404, statusText: "Not found" });
  });

  describe("#createClassReservation", () => {
    it("a visszatérési érték: Observable<ClassReservation>", () => {
      service
        .createClassReservation(testReservations[0])
        .subscribe(reservation => {
          expect(reservation).toEqual(testReservations[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_CREATE_RESERVATION)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testReservations[0]);
    });
  });

  it("ClassReservationAlredyExistsException", () => {
    const errorMsg = "Már létezik ilyen foglalás";

    service.createClassReservation(testReservations[0]).subscribe(
      reservation =>
        fail(
          "ClassReservationAlredyExistsException kellett volna hogy történjen!"
        ),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, "status");
        expect(error.error).toEqual(errorMsg, "message");
      }
    );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_CREATE_RESERVATION)
    );
    req.flush(errorMsg, { status: 404, statusText: "Not found" });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<ClassReservation>", () => {
      service
        .update(testReservations[0].id, testReservations[0])
        .subscribe(reservation => {
          expect(reservation).toEqual(testReservations[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_UPDATE) +
          `/${testReservations[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testReservations[0]);
    });
  });

  it("ClassReservationNotExistsException", () => {
    const errorMsg = "Nem létezik ilyen foglalás";

    service.update(testReservations[0].id, testReservations[0]).subscribe(
      reservation =>
        fail(
          "ClassReservationNotExistsException kellett volna hogy történjen!"
        ),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, "status");
        expect(error.error).toEqual(errorMsg, "message");
      }
    );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_UPDATE) +
        `/${testReservations[0].id}`
    );
    req.flush(errorMsg, { status: 404, statusText: "Not found" });
  });

  describe("#existsById", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsById(testReservations[0].id).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_EXISTS_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsById(testReservations[0].id).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_EXISTS_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
