import {
  ValidationErrors,
  ValidatorFn,
  FormGroup
} from "@angular/forms";

export const passwordValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');
  if (password.pristine || confirmPassword.pristine) {
    return null;
  }
  return password && confirmPassword && password.value !== confirmPassword.value ? { 'passwordMisMatch': true } : null;
};
