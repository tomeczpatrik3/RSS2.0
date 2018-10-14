import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { SubjectService } from "../services/subject.service";

@Injectable({
  providedIn: "root"
})
export class UniqueSubjectCodeValidator implements AsyncValidator {
  constructor(private subjectService: SubjectService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.subjectService.existsByCode(ctrl.value).pipe(
      map(isTaken => (isTaken ? { isTaken: true } : null)),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[uniqueSubjectCode]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueSubjectCodeValidator),
      multi: true
    }
  ]
})
export class UniqueSubjectCodeValidatorDirective {
  constructor(private validator: UniqueSubjectCodeValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
