import { Component, OnInit } from "@angular/core";
import { NavigationService } from "../../services/navigation.service";
import { MenuItem } from "../../models/MenuItem";
import { AuthService } from "../../services/auth.service";
import { prefix } from "../../../app-routing.module";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  /*A menü*/
  menuItems: MenuItem[] = [];

  /*A főoldal linkje*/
  dashboardLink: string = `${prefix}/dashboard`;
  /*A bejelentkezés linkje*/
  loginLink: string = `${prefix}/users/login`;
  /*A regisztráció linkje*/
  registerLink: string = `${prefix}/users/register`;

  constructor(
    private navigationService: NavigationService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    /*A menü inicializálása az engedélyeknek megfelelően*/
    this.menuItems = this.navigationService.getMenu(
      this.authService.getAuthorities()
    );

    /*Minden egyes ki-/bejelentkezésnél frissítjük a menüt*/
    this.authService.loginStatus.subscribe(res => {
      this.menuItems = this.navigationService.getMenu(res);
    });
  }

  /**
   * A függvény ami visszaadja hogy be vagyunk-e jelentkezve vagy sem
   */
  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  /**
   * A függvény ami visszaadja a bejelentkezett személy felhasználónevét
   */
  getName(): string {
    return this.authService.getUsername();
  }

  /**
   * A kijelentkezést végrehajtó függvény
   */
  logOut(): void {
    this.authService.logOut();
  }
}
