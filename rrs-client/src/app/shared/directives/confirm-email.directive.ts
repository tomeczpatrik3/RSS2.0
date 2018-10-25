import { ValidationErrors, ValidatorFn, FormGroup } from "@angular/forms";

/**
 * Az e-mail címek egyezését validáló függvény
 * @param control A FormGroup objektum
 */
export const emailValidator: ValidatorFn = (
  control: FormGroup
): ValidationErrors | null => {
  const email = control.get("email");
  const confirmEmail = control.get("confirmEmail");
  if (email.pristine || confirmEmail.pristine) {
    return null;
  }
  return email && confirmEmail && email.value !== confirmEmail.value
    ? { emailMisMatch: true }
    : null;
};
