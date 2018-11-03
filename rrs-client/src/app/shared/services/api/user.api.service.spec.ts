import { UserApiService } from "./user.api.service";
import {
  HttpTestingController,
  HttpClientTestingModule
} from "@angular/common/http/testing";
import { TestBed, getTestBed } from "@angular/core/testing";
import { User } from "../../models/User";
import { ApiEndpoints } from "../../config/api-endpoints.config";
import {
  HttpErrorResponse,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";

describe("UserApiService", () => {
  let injector;
  let service: UserApiService;
  let httpMock: HttpTestingController;
  let testUsers: User[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserApiService]
    });

    injector = getTestBed();
    service = injector.get(UserApiService);
    httpMock = injector.get(HttpTestingController);

    testUsers = [
      new User("tesztJani", "asd123", "Teszt János", "tesztjani@gmail.com", 1),
      new User("tesztGabi", "asd123", "Teszt Gábor", "tesztgabii@gmail.com", 2)
    ];
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("#getAll", () => {
    it("a visszatérési érték: Observable<User[]>", () => {
      service.getAll().subscribe(users => {
        expect(users.length).toBe(2);
        expect(users).toEqual(testUsers);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_GET_ALL)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testUsers);
    });
  });

  describe("#getNames", () => {
    it("a visszatérési érték: Observable<string[]>", () => {
      const testNames: string[] = ["Teszt János", "Teszt Gábor"];

      service.getNames().subscribe(names => {
        expect(names.length).toBe(2);
        expect(names).toEqual(testNames);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_GET_NAMES)
      );
      expect(req.request.method).toBe("GET");
      req.flush(testNames);
    });
  });

  describe("#getNameByUsername", () => {
    it("a visszatérési érték: Observable<any>", () => {
      service.getNameByUsername(testUsers[0].username).subscribe(name => {
        expect(name).toEqual(testUsers[0].username);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_GET_NAME_BY_USERNAME) +
          `?username=${testUsers[0].username}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testUsers[0].username);
    });

    it("UserNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen felhasználó";

      service.getNameByUsername(testUsers[0].username).subscribe(
        name => fail("UserNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_GET_NAME_BY_USERNAME) +
          `?username=${testUsers[0].username}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findById", () => {
    it("a visszatérési érték: Observable<User>", () => {
      service.findById(testUsers[0].id).subscribe(user => {
        expect(user).toEqual(testUsers[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_FIND_BY_ID) +
          `/${testUsers[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testUsers[0]);
    });

    it("UserNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen felhasználó";

      service.findById(testUsers[0].id).subscribe(
        user => fail("UserNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_FIND_BY_ID) +
          `/${testUsers[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByUsername", () => {
    it("a visszatérési érték: Observable<User>", () => {
      service.findByUsername(testUsers[0].username).subscribe(user => {
        expect(user).toEqual(testUsers[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_FIND_BY_USERNAME) +
          `?username=${testUsers[0].username}`
      );
      expect(req.request.method).toBe("GET");
      req.flush(testUsers[0]);
    });

    it("UserNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen felhasználó";

      service.findByUsername(testUsers[0].username).subscribe(
        user => fail("UserNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_FIND_BY_USERNAME) +
          `?username=${testUsers[0].username}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#findByName", () => {
    it("a visszatérési érték: Observable<User[]>", () => {
      service.findByName(testUsers[0].name).subscribe(users => {
        expect(users.length).toBe(1);
        expect(users).toEqual([testUsers[0]]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_FIND_BY_NAME) +
          `?name=${testUsers[0].name}`
      );
      expect(req.request.method).toBe("GET");
      req.flush([testUsers[0]]);
    });
  });

  describe("#createUser", () => {
    it("a visszatérési érték: Observable<User>", () => {
      service.createUser(testUsers[0]).subscribe(user => {
        expect(user).toEqual(testUsers[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_CREATE_USER)
      );
      expect(req.request.method).toBe("POST");
      req.flush(testUsers[0]);
    });

    it("UserAlredyExistsException", () => {
      const errorMsg = "Már létezik ez a felhasználó";

      service.createUser(testUsers[0]).subscribe(
        user => fail("UserAlredyExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_CREATE_USER)
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#update", () => {
    it("a visszatérési érték: Observable<User>", () => {
      service.update(testUsers[0].id, testUsers[0]).subscribe(user => {
        expect(user).toEqual(testUsers[0]);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_UPDATE) + `/${testUsers[0].id}`
      );
      expect(req.request.method).toBe("PUT");
      req.flush(testUsers[0]);
    });

    it("UserNotExistsException", () => {
      const errorMsg = "Nem létezik ilyen felhasználó";

      service.update(testUsers[0].id, testUsers[0]).subscribe(
        name => fail("UserNotExistsException kellett volna hogy történjen!"),
        (error: HttpErrorResponse) => {
          expect(error.status).toEqual(404, "status");
          expect(error.error).toEqual(errorMsg, "message");
        }
      );

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_UPDATE) + `/${testUsers[0].id}`
      );
      req.flush(errorMsg, { status: 404, statusText: "Not found" });
    });
  });

  describe("#existsById", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsById(testUsers[0].id).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_EXISTS_BY_ID) +
          `/${testUsers[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsById(testUsers[0].id).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_EXISTS_BY_ID) +
          `/${testUsers[0].id}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });

  describe("#existsByUsername", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsByUsername(testUsers[0].username).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_EXISTS_BY_USERNAME) +
          `?username=${testUsers[0].username}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsByUsername(testUsers[0].username).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_EXISTS_BY_USERNAME) +
          `?username=${testUsers[0].username}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });

  describe("#existsByEmail", () => {
    it("a visszatérési érték: Observable<boolean> (true)", () => {
      service.existsByEmail(testUsers[0].email).subscribe(result => {
        expect(result).toEqual(true);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_EXISTS_BY_EMAIL) +
          `?email=${testUsers[0].email}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: true }));
    });

    it("a visszatérési érték: Observable<boolean> (false)", () => {
      service.existsByEmail(testUsers[0].email).subscribe(result => {
        expect(result).toEqual(false);
      });

      const req = httpMock.expectOne(
        ApiEndpoints.getUrl(ApiEndpoints.USER_EXISTS_BY_EMAIL) +
          `?email=${testUsers[0].email}`
      );
      expect(req.request.method).toBe("GET");
      req.event(new HttpResponse<boolean>({ body: false }));
    });
  });
});
