import { Injectable } from "@angular/core";
import { HttpInterceptor } from "@angular/common/http/src/interceptor";
import { HttpRequest } from "@angular/common/http/src/request";
import { HttpHandler } from "@angular/common/http/src/backend";
import { Observable } from "rxjs/Observable";
import { HttpEvent } from "@angular/common/http/src/response";

/*
    Token hozzáadása a headerhez (ha van)
*/
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>,
              next: HttpHandler): Observable<HttpEvent<any>> {

        const token = localStorage.getItem("token");

        if (token) {
            const cloned = req.clone({
                headers: req.headers.set("Authorization",
                    token)
            });

            return next.handle(cloned);
        }
        else {
            return next.handle(req);
        }
    }
}