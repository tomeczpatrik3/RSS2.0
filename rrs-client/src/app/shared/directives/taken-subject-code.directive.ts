import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { SubjectApiService } from "../services/api/subject.api.service";

@Injectable({
  providedIn: "root"
})
export class TakenSubjectCodeValidator implements AsyncValidator {
  constructor(private subjectService: SubjectApiService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    const code = ctrl.value.split("|")[0].trim();
    if (code) {
      return this.subjectService.existsByCode(code).pipe(
        map(isTaken => (isTaken ? null : { isUnique: true })),
        catchError(() => null)
      );
    }
  }
}

@Directive({
  selector: "[takenSubjectCode]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => TakenSubjectCodeValidator),
      multi: true
    }
  ]
})
export class TakenSubjectCodeValidatorDirective {
  constructor(private validator: TakenSubjectCodeValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
