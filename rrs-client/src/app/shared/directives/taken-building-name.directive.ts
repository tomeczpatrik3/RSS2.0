import { Directive, forwardRef, Injectable } from "@angular/core";
import {
  AsyncValidator,
  AbstractControl,
  NG_ASYNC_VALIDATORS,
  ValidationErrors
} from "@angular/forms";
import { catchError, map } from "rxjs/operators";
import { Observable } from "rxjs";
import { BuildingApiService } from "../services/api/building.api.service";

@Injectable({
  providedIn: "root"
})
/**
 * A validátor ami azt vizsgálja, hogy egy adott épület név
 * létezik-e és hibát jelez, ha nem
 */
export class TakenBuildingNameValidator implements AsyncValidator {
  constructor(private buildingService: BuildingApiService) {}

  /**
   * A validálást végző függvény
   * @param ctrl A formcontrol
   */
  validate(
    ctrl: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return this.buildingService.existsByName(ctrl.value).pipe(
      map(isTaken => (isTaken ? null : { isUnique: true })),
      catchError(() => null)
    );
  }
}

@Directive({
  selector: "[takenBuildingName]",
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: forwardRef(() => TakenBuildingNameValidator),
      multi: true
    }
  ]
})
/**
 * TakenBuildingNameValidator direktíva
 */
export class TakenBuildingNameValidatorDirective {
  constructor(private validator: TakenBuildingNameValidator) {}

  validate(control: AbstractControl) {
    this.validator.validate(control);
  }
}
