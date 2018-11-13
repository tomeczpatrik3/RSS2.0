import { Injectable } from "@angular/core";
import { Router, CanActivate, ActivatedRouteSnapshot } from "@angular/router";
import { AuthService } from "../services/auth.service";

/**
 * Az engedélyek ellenőrzéséért felelős guard
 */
@Injectable()
export class RoleGuard implements CanActivate {
  constructor(public authService: AuthService, public router: Router) {}

  /**
   * canActivate() függvény
   */
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const authority = route.data.authority;

    if (!this.authService.isLoggedIn()) {
      this.router.navigate(["login"]);
      return false;
    } else if (this.authService.getAuthorities().indexOf(authority) == -1) {
      this.router.navigate(["dashboard"]);
      return false;
    } else {
      return true;
    }
  }
}
