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
export class TakenEventNameValidator implements AsyncValidator {
  constructor(private eventReservationService: EventReservationService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.eventReservationService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? null : { isUnique: true })),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[takenEventName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => TakenEventNameValidator),
      multi: true
    }
  ]
})
export class TakenEventNameValidatorDirective {
  constructor(private validator: TakenEventNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
