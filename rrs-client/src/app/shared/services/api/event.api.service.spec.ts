import {
  HttpTestingController,
  HttpClientTestingModule
} from "@angular/common/http/testing";
import { TestBed, getTestBed } from "@angular/core/testing";
import { Event } from "../../models/Event";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import { EventApiService } from "./event.api.service";
import { ReservationInfo } from "../../models/ReservationInfo";

describe("EventApiService", () => {
  let injector;
  let service: EventApiService;
  let httpMock: HttpTestingController;
  let testEvents: Event[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EventApiService]
    });

    injector = getTestBed();
    service = injector.get(EventApiService);
    httpMock = injector.get(HttpTestingController);

    testEvents = [
      new Event(
        "Teszt esemény 1",
        "2018.11.03 10:00",
        "2018.11.03 13:00",
        new ReservationInfo(
          1,
          "CLASS",
          "Teszt János",
          "ÉSZAKI",
          "Harmómia terem",
          "Ez egy teszt",
          "",
          "2018-2019/1",
          "Analízis 1 EA"
        )
      ),
      new Event(
        "Teszt esemény 2",
        "2018.11.04 15:00",
        "2018.11.04 15:30",
        new ReservationInfo(
          1,
          "EVENT",
          "Teszt Gábot",
          "DÉLI",
          "Kitaibel Pál tanterem",
          "Ez egy teszt",
          "Teszt foglalás 1",
          "",
          ""
        )
      )
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getEvents", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service.getEvents().subscribe(events => {
        expect(events.length).toBe(2);
        expect(events).toEqual(testEvents);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_GET_EVENTS)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testEvents);
    });
  });

  describe("#findByUserName", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service.findByUserName(testEvents[0].info.name).subscribe(events => {
        expect(events.length).toBe(1);
        expect(events).toEqual([testEvents[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_USER_NAME) +
          `?userName=${testEvents[0].info.name}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testEvents[0]]);
    });
  });

  describe("#findByBuildingName", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service
        .findByBuildingName(testEvents[0].info.building)
        .subscribe(events => {
          expect(events.length).toBe(1);
          expect(events).toEqual([testEvents[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_BUILDING_NAME) +
          `?buildingName=${testEvents[0].info.building}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testEvents[0]]);
    });
  });

  describe("#findByClassroomAndBuilding", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service
        .findByClassroomAndBuilding(
          testEvents[0].info.classroom,
          testEvents[0].info.building
        )
        .subscribe(events => {
          expect(events.length).toBe(1);
          expect(events).toEqual([testEvents[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_CLASSROOM_AND_BUILDING) +
          `?classroom=${testEvents[0].info.classroom}&building=${
            testEvents[0].info.building
          }`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testEvents[0]]);
    });
  });

  describe("#findByEventName", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service
        .findByEventName(testEvents[0].info.eventName)
        .subscribe(events => {
          expect(events.length).toBe(1);
          expect(events).toEqual([testEvents[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_EVENT_NAME) +
          `?eventName=${testEvents[0].info.eventName}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testEvents[0]]);
    });
  });

  describe("#findBySubjectName", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service
        .findBySubjectName(testEvents[0].info.subject)
        .subscribe(events => {
          expect(events.length).toBe(1);
          expect(events).toEqual([testEvents[0]]);
        });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_SUBJECT_NAME) +
          `?subjectName=${testEvents[0].info.subject}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testEvents[0]]);
    });
  });

  describe("#findBySemesterName", () => {
    it("a visszatérési érték: Observable<Event[]>", () => {
      service.findBySemesterName(testEvents[0].info.semester).subscribe(events => {
        expect(events.length).toBe(1);
        expect(events).toEqual([testEvents[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.EVENT_FIND_BY_SEMESTER_NAME) +
          `?semesterName=${testEvents[0].info.semester}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testEvents[0]]);
    });
  });
});
