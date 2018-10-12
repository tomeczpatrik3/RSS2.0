import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { BuildingService } from "../services/building.service";

@Injectable({
  providedIn: "root"
})
export class UniqueBuildingNameValidator implements AsyncValidator {
  constructor(private buildingService: BuildingService) {}

  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.buildingService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? null : { uniqueBuildingName: true })),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[uniqueBuildingName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => UniqueBuildingNameValidator),
      multi: true
    }
  ]
})
export class UniqueBuildingNameValidatorDirective {
  constructor(private validator: UniqueBuildingNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
