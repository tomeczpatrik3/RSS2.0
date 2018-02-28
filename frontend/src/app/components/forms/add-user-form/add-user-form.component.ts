import { Component, OnInit, ViewChild} from '@angular/core';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/User';
import { SnackbarService } from '../../../services/snackbar.service';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent implements OnInit {

  username = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  name = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  password = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  passwordAgain = new FormControl('', [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(30)
  ]);

  email = new FormControl('', [
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
    private dialog: MatDialog
  ) { }

  ngOnInit() {
  }

  formToUser(): User {
    return new User(
      this.userForm.value.username,
      this.userForm.value.password,
      this.userForm.value.name,
      this.userForm.value.email,
      "USER"
    );
  }

  /*
    Feliratkozunk, majd:
    - hiba esetén jelzünk a hibát dialog segítségével
    - siker esetén jelezzük a sikert dialog segítségével
  */
  addUser() {
    if (this.userForm.value.password != this.userForm.value.passwordAgain) {
      this.openDialog("Felhasználó hozzáadása:", "Hiba történt, a jelszavak nem egyeznek!")
    }
    else {
      this.userService.createUser(this.formToUser()).subscribe(
        res => console.log(res),
        error => this.openDialog("Felhasználó hozzáadása:", "Hiba történt"),
        () => this.openDialog("Felhasználó hozzáadása:", "Felhasználó sikeresen rögítve")
      );      
    }
    this.userForm.reset();
  }

  /*
    Dialog megjelenítése, valamint adatok átadása:
    title: a dialog címe
    text: a dialogban közölt üzenet

    Dialog bezárása után reseteljük a formot!
  */
  openDialog(title_: string, text_: string) {
    let dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '600px',
      data: {
        title: title_,
        text: text_
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.userForm.reset();
    })
  }
}
