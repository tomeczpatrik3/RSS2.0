import { Component, OnInit, ViewChild } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { UserService } from "../../../services/user.service";
import { User } from "../../../models/User";
import { SnackbarService } from "../../../services/snackbar.service";
import { MatDialog } from "@angular/material";
import { InfoDialogComponent } from "../../dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../services/dialog.service";
import { Observable } from "rxjs";
import { MessageConstants } from "../../../config/message-constants.config";
import { QuestionDialogComponent } from "../../dialogs/question-dialog/question-dialog.component";
import { TextUtils } from "../../../utils/text-utils";

@Component({
  selector: "app-add-user-form",
  templateUrl: "./add-user-form.component.html",
  styleUrls: ["./add-user-form.component.css"]
})
export class AddUserFormComponent implements OnInit {
  username = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  name = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  password = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  passwordAgain = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  email = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(50)
  ]);

  userForm: FormGroup = this.builder.group({
    username: this.username,
    name: this.name,
    password: this.password,
    passwordAgain: this.passwordAgain,
    email: this.email
  });

  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private snackBarService: SnackbarService,
    private dialogService: DialogService
  ) {}

  ngOnInit() {}

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
    if (this.userForm.value.password != this.userForm.value.passwordAgain) {
      this.dialogService.openDialog(
        "Felhasználó hozzáadása:",
        "Hiba történt, a jelszavak nem egyeznek!",
        InfoDialogComponent
      );
    } else {
      this.userService
        .createUser(this.formToUser())
        .subscribe(
          res => console.log(res),
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
    }
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
