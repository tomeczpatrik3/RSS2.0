import { Component, OnInit } from "@angular/core";
import {
  Validators,
  FormGroup,
  FormControl,
  FormBuilder
} from "@angular/forms";
import { AccountCredentials } from "../../../../shared/models/AccountCredentials";
import { AuthService } from "../../../../shared/services/auth.service";
import { NavigationService } from "../../../../shared/services/navigation.service";

@Component({
  selector: "app-login-form",
  templateUrl: "./login-form.component.html",
  styleUrls: ["./login-form.component.css"]
})
export class LoginFormComponent implements OnInit {
  /*A felhasználónév*/
  username = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  /*A jelszó*/
  password = new FormControl("", [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  /*Az űrlap*/
  loginForm: FormGroup = this.builder.group({
    username: this.username,
    password: this.password
  });

  constructor(
    private builder: FormBuilder,
    private authService: AuthService,
    private navService: NavigationService
  ) {}

  ngOnInit() {}

  /**
   * A bejelentkezésért felelős függvény
   */
  login() {
    this.authService.login(
      new AccountCredentials(
        this.loginForm.value.username,
        this.loginForm.value.password
      )
    );
    this.loginForm.reset();
  }
}
