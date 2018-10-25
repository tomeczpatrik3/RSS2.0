import { Injectable } from "@angular/core";
import { Router, CanActivate } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { prefix } from "../../app-routing.module";

/**
 * Az autentikációért felelős guard
 */
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(public authService: AuthService, public router: Router) {}

  /**
   * canActivate() függvény
   * -- igaz, ha a felhasználó be van jelentkezve
   * -- hamis egyébként
   */
  canActivate(): boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate([`${prefix}/users/login`]);
      return false;
    }
    return true;
  }
}
