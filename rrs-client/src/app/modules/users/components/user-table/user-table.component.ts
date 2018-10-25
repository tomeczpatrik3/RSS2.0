import { Component, OnInit } from "@angular/core";
import { User } from "../../../../shared/models/User";
import { UsersDataService } from "../../users.data.service";

@Component({
  selector: "app-user-table",
  templateUrl: "./user-table.component.html",
  styleUrls: ["./user-table.component.css"]
})
export class UserTableComponent implements OnInit {
  /*A felhasználók*/
  users: User[];

  constructor(private userService: UsersDataService) {}

  /**
   * Az inicializálásért felelős függvény
   * A felhasználók betöltése
   */
  ngOnInit() {
    this.userService.getAll().subscribe(res => (this.users = res));
  }
}
