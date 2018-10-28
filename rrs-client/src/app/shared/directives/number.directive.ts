import { Directive } from "@angular/core";
import {
  NG_VALIDATORS,
  AbstractControl,
  ValidatorFn,
  Validator,
  FormControl
} from "@angular/forms";

/**
 * A számokat validáló függvény
 * @param control A FormGroup objektum
 */
export function numberValidator(): ValidatorFn {
  return (control: AbstractControl) => {
    let value = control.value;
    if (parseFloat(value) == parseInt(value) && !isNaN(value)) {
      return null;
    } else {
      return { notNumeric: true };
    }
  };
}

@Directive({
  selector: "[numberValidator]",
  providers: [
    { provide: NG_VALIDATORS, useExisting: NumberValidatorDirective, multi: true }
  ]
})
/**
 * NumberValidator direktíva
 */
export class NumberValidatorDirective implements Validator {
  validator: ValidatorFn;

  constructor() {
    this.validator = numberValidator();
  }

  validate(c: FormControl) {
    return this.validator(c);
  }
}
