import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { UserService } from "../services/user.service";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class UniqueUsernameValidator implements AsyncValidator {
  constructor(private userService: UserService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.userService.existsByUsername(ctrl.value).pipe(
      map(isTaken => (isTaken ? { isTaken: true } : null)),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[uniqueUsername]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueUsernameValidator),
      multi: true
    }
  ]
})
export class UniqueUsernameValidatorDirective {
  constructor(private validator: UniqueUsernameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
