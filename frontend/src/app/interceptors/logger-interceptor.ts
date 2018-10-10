import { Injectable } from "@angular/core";
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";

import { Observable } from "rxjs";

@Injectable()
export class LoggerInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
/*     if (req instanceof HttpResponse) {
      console.log(`Státusz kód: ${req.status}`);
      console.log(`A válasz: ${req.body}`);
    } */
    if (req.body) {
        console.log('A válasz:');
        console.log(req.body);
    }

    return next.handle(req);
  }
}
