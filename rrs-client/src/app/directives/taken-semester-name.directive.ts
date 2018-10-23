import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { SemesterService } from "../services/semester.service";

@Injectable({
  providedIn: "root"
})
export class TakenSemesterNameValidator implements AsyncValidator {
  constructor(private semesterService: SemesterService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.semesterService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? null : { isUnique: true })),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[takenSemesterName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => TakenSemesterNameValidator),
      multi: true
    }
  ]
})
export class TakenSemesterNameValidatorDirective {
  constructor(private validator: TakenSemesterNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
