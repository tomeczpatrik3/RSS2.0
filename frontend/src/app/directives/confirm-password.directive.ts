import {
  ValidationErrors,
  ValidatorFn,
  FormGroup
} from "@angular/forms";

export const passwordValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const password = control.get('password');
  const passwordAgain = control.get('passwordAgain');
  console.log("-------");
  return password && passwordAgain && password.value === passwordAgain.value ? null : { 'mismatchingPasswords': true };
};
