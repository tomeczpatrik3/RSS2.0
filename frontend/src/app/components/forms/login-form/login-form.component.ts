import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { AccountCredentials } from '../../../models/AccountCredentials';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  username = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  password = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  loginForm: FormGroup = this.builder.group({
    username: this.username,
    password: this.password
  });

  constructor(
    private builder: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit() {
  }

  login() {
    this.userService.login(new AccountCredentials(this.loginForm.value.username, this.loginForm.value.password));

    this.loginForm.reset();
  }
}
