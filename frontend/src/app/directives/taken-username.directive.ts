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
export class TakenUsernameValidator implements AsyncValidator {
  constructor(private userService: UserService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.userService.existsByUsername(ctrl.value).pipe(
      map(isTaken => (isTaken ? null : { isUnique: true })),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[takenUsername]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => TakenUsernameValidator),
      multi: true
    }
  ]
})
export class TakenUsernameValidatorDirective {
  constructor(private validator: TakenUsernameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
