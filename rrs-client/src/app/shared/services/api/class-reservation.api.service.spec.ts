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

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClassReservationApiService]
    });

    injector = getTestBed();
    service = injector.get(ClassReservationApiService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAccepted", () => {
    it("a visszatérési érték: Observable<ClassReservation[]>", () => {
      const testReservations: ClassReservation[] = [
        new ClassReservation(
          "testUsername",
          "testBuilding",
          "testClassroom",
          "testStatus",
          "testNote",
          "testSemester",
          "testSubjectCode",
          [],
          [],
          1
        )
      ];

      service.getAccepted().subscribe(reservations => {
        expect(reservations.length).toBe(1);
        expect(reservations).toEqual(testReservations);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.CLASS_RESERVATION_GET_ACCEPTED)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testReservations);
    });
  });
});
