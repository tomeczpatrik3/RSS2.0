import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { LoggerInterceptor } from './logger-interceptor';

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: LoggerInterceptor, multi: true },
];