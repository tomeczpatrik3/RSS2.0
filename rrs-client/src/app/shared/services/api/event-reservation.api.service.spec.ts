import { TestBed, getTestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController
} from "@angular/common/http/testing";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { EventReservationApiService } from "./event-reservation.api.service";
import { EventReservation } from "../../models/EventReservation";

describe("EventReservationApiService", () => {
  let injector;
  let service: EventReservationApiService;
  let httpMock: HttpTestingController;
  let testReservations: EventReservation[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EventReservationApiService]
    });

    injector = getTestBed();
    service = injector.get(EventReservationApiService);
    httpMock = injector.get(HttpTestingController);

    testReservations = [
      new EventReservation(
        "testJani",
        "Teszt épület 1",
        "Teszt tanterem 1",
        "ACCEPTED",
        "Ez egy megjegyzés",
        "Az első teszt esemény",
        "2011.10.05 10:30",
        "2011.10.05 12:30",
        1
      ),
      new EventReservation(
        "tesztGabi",
        "Teszt épület 2",
        "Teszt tanterem 2",
        "PENDING",
        "Ez egy megjegyzés",
        "A második teszt esemény",
        "2012.05.05 10:30",
        "2011.05.05 12:30",
        2
      ),
      new EventReservation(
        "tesztGabi",
        "Teszt épület 2",
        "Teszt tanterem 2",
        "DECLINED",
        "Ez egy megjegyzés",
        "A második teszt esemény",
        "2012.05.05 10:30",
        "2011.05.05 12:30",
        3
      )
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAccepted", () => {
    it("a visszatérési érték: Observable<EventReservation[]>", () => {
      service.getAccepted().subscribe(reservations => {
        expect(reservations.length).toBe(1);
        expect(reservations).toEqual([testReservations[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_GET_ACCEPTED)
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#getPending", () => {
    it("a visszatérési érték: Observable<EventReservation[]>", () => {
      service.getPending().subscribe(reservations => {
        expect(reservations.length).toBe(1);
        expect(reservations).toEqual([testReservations[1]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_GET_PENDING)
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[1]]);
    });
  });

  describe("#getEventNames", () => {
    it("a visszatérési érték: Observable<string[]>", () => {
      const testNames: string[] = [
        "Az első teszt esemény",
        "A második teszt esemény",
        "A harmadik teszt esemény"
      ];

      service.getEventNames().subscribe(names => {
        expect(names.length).toBe(3);
        expect(names).toEqual(testNames);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_GET_NAMES)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testNames);
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<EventReservation[]>", () => {
      service.findById(testReservations[0].id).subscribe(reservation => {
        expect(reservation).toEqual(testReservations[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testReservations[0]);
    });

    it("EventReservationNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen foglalás";

      service.findById(testReservations[0].id).subscribe(
        reservation =>
          fail(
            "EventReservationNotExistsException kellett volna hogy történjen!"
          ),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByUsername", () => {
    it("a visszatérési érték: Observable<EventReservation[]>", () => {
      service
        .findByUsername(testReservations[0].username)
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_USERNAME) +
          "/" +
          testReservations[0].username
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findByStatus", () => {
    it("a visszatérési érték: Observable<EventReservation[]>", () => {
      service
        .findByStatus(testReservations[0].status)
        .subscribe(reservations => {
          expect(reservations.length).toBe(1);
          expect(reservations).toEqual([testReservations[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(
          ApiEndpoints.EVENT_RESERVATION_FIND_BY_STATUS +
            "/" +
            testReservations[0].status
        )
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findByBuildingAndClassroom", () => {
    it("a visszatérési érték: Observable<EventReservation[]>", () => {
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
          ApiEndpoints.EVENT_RESERVATION_FIND_BY_BUILDING_AND_CLASSROOM
        ) +
          `?building=${testReservations[0].building}&classroom=${
            testReservations[0].classroom
          }`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testReservations[0]]);
    });
  });

  describe("#findByName", () => {
    it("a visszatérési érték: Observable<EventReservation>", () => {
      service.findByName(testReservations[0].name).subscribe(reservation => {
        expect(reservation).toEqual(testReservations[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_NAME) +
          "/" +
          testReservations[0].name
      );
      expect(req.request.method).toBe("GET");
      req.flush(testReservations[0]);
    });

    it("EventReservationNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen foglalás";

      service.findByName(testReservations[0].name).subscribe(
        reservation =>
          fail(
            "EventReservationNotExistsException kellett volna hogy történjen!"
          ),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_FIND_BY_NAME) +
          "/" +
          testReservations[0].name
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#setStatus", () => {
    it("a visszatérési érték: Observable<EventReservation>", () => {
      let testRes = testReservations[0];
      testRes.status = "DECLINED";

      service
        .setStatus(testReservations[0].id, "DECLINED")
        .subscribe(reservation => {
          expect(reservation).toEqual(testRes);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_SET_STATUS) +
          `?id=${testReservations[0].id}&status=${"DECLINED"}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testRes);
    });
  });

  it("EventReservationNotExistsException", () => {
    const errorMsg = "Nem létezik ilyen foglalás";

    service.setStatus(testReservations[0].id, "DECLINED").subscribe(
      reservation =>
        fail(
          "EventReservationNotExistsException kellett volna hogy történjen!"
        ),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, "status");
        expect(error.error).toEqual(errorMsg, "message");
      }
    );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_SET_STATUS) +
        `?id=${testReservations[0].id}&status=${"DECLINED"}`
    );
    req.flush(errorMsg, { status: 404, statusText: "Not found" });
  });

  describe("#createEventReservation", () => {
    it("a visszatérési érték: Observable<EventReservation>", () => {
      service
        .createEventReservation(testReservations[0])
        .subscribe(reservation => {
          expect(reservation).toEqual(testReservations[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_CREATE_RESERVATION)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testReservations[0]);
    });
  });

  it("EventReservationAlredyExistsException", () => {
    const errorMsg = "Már létezik ilyen foglalás";

    service.createEventReservation(testReservations[0]).subscribe(
      reservation =>
        fail(
          "EventReservationAlredyExistsException kellett volna hogy történjen!"
        ),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, "status");
        expect(error.error).toEqual(errorMsg, "message");
      }
    );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_CREATE_RESERVATION)
    );
    req.flush(errorMsg, { status: 404, statusText: "Not found" });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<EventReservation>", () => {
      service
        .update(testReservations[0].id, testReservations[0])
        .subscribe(reservation => {
          expect(reservation).toEqual(testReservations[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_UPDATE) +
          `/${testReservations[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testReservations[0]);
    });
  });

  it("EventReservationNotExistsException", () => {
    const errorMsg = "Nem létezik ilyen foglalás";

    service.update(testReservations[0].id, testReservations[0]).subscribe(
      reservation =>
        fail(
          "EventReservationNotExistsException kellett volna hogy történjen!"
        ),
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404, "status");
        expect(error.error).toEqual(errorMsg, "message");
      }
    );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_UPDATE) +
        `/${testReservations[0].id}`
    );
    req.flush(errorMsg, { status: 404, statusText: "Not found" });
  });

  describe("#updateOwnById", () => {
    it("a visszatérési érték: Observable<EventReservation>", () => {
      service
        .updateOwnById(testReservations[0].id, testReservations[0])
        .subscribe(reservation => {
          expect(reservation).toEqual(testReservations[0]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_UPDATE_OWN_BY_ID) +
          `/${testReservations[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testReservations[0]);
    });
  });

  it("EventReservationNotExistsException", () => {
    const errorMsg = "Nem létezik ilyen foglalás";

    service
      .updateOwnById(testReservations[0].id, testReservations[0])
      .subscribe(
        reservation =>
          fail(
            "EventReservationNotExistsException kellett volna hogy történjen!"
          ),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

    const req = httpMock.expectOne(
      ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_UPDATE_OWN_BY_ID) +
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
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_EXISTS_BY_ID) +
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
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_EXISTS_BY_ID) +
          `?id=${testReservations[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });

  describe("#existsByName", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsByName(testReservations[0].name).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_EXISTS_BY_NAME) +
          `?name=${testReservations[0].name}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsByName(testReservations[0].name).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_RESERVATION_EXISTS_BY_NAME) +
          `?name=${testReservations[0].name}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
