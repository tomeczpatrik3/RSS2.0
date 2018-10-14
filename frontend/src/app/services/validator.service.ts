import { Injectable } from "@angular/core";
import { FormControl } from "@angular/forms";

/**
 * A függvény ami ellenőrzi hogy a paraméterben átadott érték szám-e
 * @param value Az érték
 */
export function check_if_is_integer(value) {
  if (parseFloat(value) == parseInt(value) && !isNaN(value)) {
    return true;
  } else {
    return false;
  }
}

/**
 * A függvény ami ellenőrzi hogy a paraméterben átadott érték idő-e
 * @param value Az érték
 */
export function check_if_is_time(value) {
  if (value) {
    let splitted = value.split(":");
    if (check_if_is_integer(splitted[0]) && check_if_is_integer(splitted[1])) {
      return true;
    } else {
      return false;
    }
  }
}

/**
 * A függvény ami ellenőrzi hogy a paraméterben átadott kezdési idő megelőzi-e a befejezési időt
 * Használat check_if_is_time() hívás után!!
 * @param startTime A kezdési idő
 * @param endTime A befejezési idő
 */
export function isBefore(startTime: string, endTime: string): boolean {
  const startHour = startTime.split(":")[0];
  const startMin = startTime.split(":")[1];
  const endHour = endTime.split(":")[0];
  const endMin = endTime.split(":")[1];

  if (startHour < endHour) {
    return true;
  } else if (startHour == endHour && startMin < endMin) {
    return true;
  } else {
    return false;
  }
}

/**
 * A validálást végző metódusokat tartalmazó osztály
 */
@Injectable()
export class ValidatorService {
  /**
   * Az egész szám validálását végző függvény
   */
  public isInteger = (control: FormControl) => {
    return check_if_is_integer(control.value)
      ? null
      : {
          notNumeric: true
        };
  };

  /**
   * Az idő validálását végző függvény
   */
  public isTime = (control: FormControl) => {
    return check_if_is_time(control.value)
      ? null
      : {
          notTime: true
        };
  };
}
