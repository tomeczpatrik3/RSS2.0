import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { EventReservationService } from "../services/event-reservation.service";

@Injectable({
  providedIn: "root"
})
export class UniqueEventReservationNameValidator implements AsyncValidator {
  constructor(private eventReservationService: EventReservationService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.eventReservationService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? null : { uniqueEventReservationName: true })),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[uniqueEventReservationName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueEventReservationNameValidator),
      multi: true
    }
  ]
})
export class UniqueEventReservationNameValidatorDirective {
  constructor(private validator: UniqueEventReservationNameValidator) {}
  
  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
