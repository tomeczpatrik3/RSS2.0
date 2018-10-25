import { Component, OnInit } from "@angular/core";
import { Validators, FormGroup, FormControl } from "@angular/forms";
import { UsersDataService } from "../../users.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { UniqueUsernameValidator } from "../../../../shared/directives/unique-username.directive";
import { passwordValidator } from "../../../../shared/directives/confirm-password.directive";
import { emailValidator } from "../../../../shared/directives/confirm-email.directive";
import { User } from "../../../../shared/models/User";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { MessageConstants } from "../../../../shared/config/message-constants.config";
import { QuestionDialogComponent } from "../../../../shared/components/dialogs/question-dialog/question-dialog.component";
import { Observable } from "rxjs";

@Component({
  selector: "app-add-user-form",
  templateUrl: "./add-user-form.component.html",
  styleUrls: ["./add-user-form.component.css"]
})
export class AddUserFormComponent implements OnInit {
  /*Az űrlap*/
  userForm: FormGroup;

  constructor(
    private userService: UsersDataService,
    private dialogService: DialogService,
    private usernameValidator: UniqueUsernameValidator
  ) {}

  ngOnInit() {
    this.userForm = new FormGroup(
      {
        username: new FormControl("", {
          validators: [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(30)
          ],
          asyncValidators: this.usernameValidator.validate.bind(
            this.usernameValidator
          ),
          updateOn: "blur"
        }),
        alterEgo: new FormControl(""),
        name: new FormControl("", [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(30)
        ]),
        password: new FormControl("", [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(30)
        ]),
        confirmPassword: new FormControl("", [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(30)
        ]),
        email: new FormControl("", [
          Validators.required,
          Validators.email,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]),
        confirmEmail: new FormControl("", [
          Validators.required,
          Validators.email,
          Validators.minLength(5),
          Validators.maxLength(50)
        ])
      },
      { validators: [passwordValidator, emailValidator] }
    );
  }

  get username() {
    return this.userForm.get("username");
  }
  get password() {
    return this.userForm.get("password");
  }
  get confirmPassword() {
    return this.userForm.get("confirmPassword");
  }
  get name() {
    return this.userForm.get("name");
  }
  get email() {
    return this.userForm.get("email");
  }
  get confirmEmail() {
    return this.userForm.get("confirmEmail");
  }

  /**
   * Az űrlap felhasználóvá konvertálását megvalósító függvény
   */
  formToUser(): User {
    return new User(
      this.userForm.value.username,
      this.userForm.value.password,
      this.userForm.value.name,
      this.userForm.value.email
    );
  }

  /**
   * A felhasználó létrehozását megvalósító függvény
   */
  addUser() {
    this.userService
      .createUser(this.formToUser())
      .subscribe(
        res => {},
        error =>
          this.dialogService.openDialog(
            "Felhasználó hozzáadása:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
          ),
        () =>
          this.dialogService.openDialog(
            "Felhasználó hozzáadása:",
            "Felhasználó sikeresen rögítve",
            InfoDialogComponent
          )
      );

    this.userForm.reset();
  }

  /**
   * Az űrlap elhagyásának engedélyezéséért/tiltásáért felelős függvény
   */
  canDeactivate(): Observable<boolean> | boolean {
    if (this.userForm.dirty) {
      return this.dialogService.openDialog(
        "Oldal elhagyása:",
        MessageConstants.FORM_QUESTION_MESSAGE,
        QuestionDialogComponent
      );
    }
    return true;
  }
}
