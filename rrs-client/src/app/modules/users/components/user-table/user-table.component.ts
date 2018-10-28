import { Component, OnInit } from "@angular/core";
import { User } from "../../../../shared/models/User";
import { UsersDataService } from "../../users.data.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-user-table",
  templateUrl: "./user-table.component.html",
  styleUrls: ["./user-table.component.css"]
})
export class UserTableComponent implements OnInit {
  /*A felhasználók*/
  users: User[];

  constructor(
    private userService: UsersDataService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A felhasználók betöltése
   */
  ngOnInit() {
    this.userService.getAll().subscribe(res => (this.users = res));
  }

  /**
   * A megfelelő dialógus megjlenítéséért felelős függvény
   * @param id A felhasználó azonosítója
   */
  openPopup(id: number) {
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService
        .openFormDialog(
          "Felhasználó szerkesztése:",
          FormType.EDIT_USER_FORM,
          id,
          FormDialogComponent
        )
        .subscribe(result => {
          if (result == true) {
            this.refreshTable();
          }
        });
    }
  }

  /**
   * A táblázat frissítéséért felelős függvény
   */
  refreshTable(): void {
    this.userService.getAll().subscribe(res => (this.users = res));
  }
}
