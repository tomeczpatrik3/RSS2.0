import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { EventReservationApiService } from "../services/api/event-reservation.api.service";

@Injectable({
  providedIn: "root"
})
export class UniqueEventNameValidator implements AsyncValidator {
  constructor(private eventReservationService: EventReservationApiService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.eventReservationService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? { isTaken: true } : null)),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[uniqueEventName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueEventNameValidator),
      multi: true
    }
  ]
})
export class UniqueEventNameValidatorDirective {
  constructor(private validator: UniqueEventNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
