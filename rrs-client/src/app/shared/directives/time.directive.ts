import { ValidationErrors, ValidatorFn, FormGroup } from "@angular/forms";
import { isBefore } from "../services/validator.service";

/**
 * Az idők validálását végző függvény
 * Ellenőrzi a formátumot, illetve azt, hogy a kezdési idő
 * megelőzi-e a befejezési időt vagy sem.
 * @param control A FormGroup objektum
 */
export const timeValidator: ValidatorFn = (
  control: FormGroup
): ValidationErrors | null => {
  const startTime = control.get("startTime");
  const endTime = control.get("endTime");
  if (startTime.pristine || endTime.pristine) {
    return null;
  } else if (
    startTime &&
    endTime &&
    startTime.valid &&
    endTime.valid &&
    isBefore(startTime.value, endTime.value)
  ) {
    //endTime.setErrors(null);
    return null;
  } else {
    //endTime.setErrors({ invalidTimes: true });
    return { invalidTimes: true };
  }
};
