import { Injectable } from '@angular/core';
import { FormControl } from '@angular/forms';

function check_if_is_integer(value){
  if((parseFloat(value) == parseInt(value)) && !isNaN(value)){ 
      return true;
  } else {
      return false;
  }
}

function check_if_is_time(value){
  let splitted = value.split(":");
  if (check_if_is_integer(splitted[0]) && check_if_is_integer(splitted[1])) {
    return true;
  }
  else {
    return false
  }
}

@Injectable()
export class ValidatorService {

  /*
    Egész szám validálása:
  */
  public isInteger = (control:FormControl) => {
    return check_if_is_integer(control.value) ? null : {
       notNumeric: true
    }
  }

  /*
    Idő validálása:
  */
  public isTime = (control:FormControl) => {
    return check_if_is_time(control.value) ? null : {
       notTime: true
    }
  }

}
