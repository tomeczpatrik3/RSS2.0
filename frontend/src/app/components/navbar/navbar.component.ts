import { Component, OnInit } from "@angular/core";
import { NavigationService } from "../../services/navigation.service";
import { MenuItem } from "../../models/MenuItem";
import { AuthService } from "../../authentication/auth.service";
import { UpperCasePipe } from "@angular/common";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  menuItems: MenuItem[] = [];

  constructor(
    private navigationService: NavigationService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.menuItems = this.navigationService.getMenu(
      this.authService.getAuthorities()
    );

    /*
      Feliratkozunk a Subject-re: 
      minden egyes változásnál frissítjük a menüt az engedélyeknek megfelelően
    */
    this.authService.loginStatus.subscribe(res => {
      this.menuItems = this.navigationService.getMenu(res);
    });
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  getName(): string {
    return this.authService.getUsername();
  }

  logOut(): void {
    this.authService.logOut();
  }
}
