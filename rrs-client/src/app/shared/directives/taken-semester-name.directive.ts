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
/**
 * A validátor ami azt vizsgálja, hogy egy adott szemeszter
 * létezik-e és hibát jelez, ha nem
 */
export class TakenSemesterNameValidator implements AsyncValidator {
  constructor(private semesterService: SemesterApiService) {}

  /**
   * A validálást végző függvény
   * @param ctrl A formcontrol
   */
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
/**
 * TakenSemesterNameValidator direktíva
 */
export class TakenSemesterNameValidatorDirective {
  constructor(private validator: TakenSemesterNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
