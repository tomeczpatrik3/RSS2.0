import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { SemesterApiService } from "../services/api/semester.api.service";

@Injectable({
  providedIn: "root"
})
export class UniqueSemesterNameValidator implements AsyncValidator {
  constructor(private semesterService: SemesterApiService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.semesterService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? { isTaken: true } : null)),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[uniqueSemesterName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueSemesterNameValidator),
      multi: true
    }
  ]
})
export class UniqueSemesterNameValidatorDirective {
  constructor(private validator: UniqueSemesterNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
