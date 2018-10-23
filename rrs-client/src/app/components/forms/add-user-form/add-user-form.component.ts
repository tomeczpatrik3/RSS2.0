import { Component, OnInit, ViewChild } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder,
  EmailValidator
} from "@angular/forms";
import { UserService } from "../../../services/user.service";
import { User } from "../../../models/User";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../config/message-constants.config";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { TextUtils } from "../../../utils/text-utils";
import { passwordValidator } from "../../../directives/confirm-password.directive";
import { emailValidator } from "../../../directives/confirm-email.directive";
import { UniqueUsernameValidator } from "../../../directives/unique-username.directive";

@Component({
  selector: "app-add-user-form",
  templateUrl: "./add-user-form.component.html",
  styleUrls: ["./add-user-form.component.css"]
})
export class AddUserFormComponent implements OnInit {
  userForm: FormGroup;

  constructor(
    private userService: UserService,
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
          asyncValidators: this.usernameValidator.validate.bind(this.usernameValidator),
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

  formToUser(): User {
    return new User(
      this.userForm.value.username,
      this.userForm.value.password,
      this.userForm.value.name,
      this.userForm.value.email
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
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
