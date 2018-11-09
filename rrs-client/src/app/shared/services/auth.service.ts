import { Injectable } from "@angular/core";
import { UserApiService } from "./api/user.api.service";
import { AccountCredentials } from "../models/AccountCredentials";
import { HttpResponse } from "@angular/common/http";

//JWT Helper:
import { JwtHelperService } from "@auth0/angular-jwt";

import { Subject } from "rxjs/Subject";
import { DialogService } from "../services/dialog.service";
import { Authorities } from "../config/authoritites.config";
import { Authentication } from "../config/authentication.config";
import { prefix } from "../../app-routing.module";
import { Router } from "@angular/router";
import { ErrorDialogComponent } from "../components/dialogs/error-dialog/error-dialog.component";

@Injectable()
export class AuthService {
  //Subject létrehozása a NavBar számára:
  loginStatus = new Subject<string[]>();

  constructor(
    private userService: UserApiService,
    private jwtHelper: JwtHelperService,
    private dialogService: DialogService,
    private router: Router,
  ) {}

  /**
   * A belépést megvalósító függvény
   * @param accountCredentials A belépéshez tartozó információk
   */
  login(accountCredentials: AccountCredentials) {
    return this.userService.login(accountCredentials).subscribe(
      (response: HttpResponse<any>) => {
        this.setSession(response);
        this.router.navigate([`${prefix}/dashboard`]);
      },
      () => {
        this.dialogService.openDialog(
          "Sikertelen bejelentkezés:",
          "Próbálkozzon újra a saját felhasználónév/jelszó kombinációjával!",
          ErrorDialogComponent
        );
      }
    );
  }

  /**
   * A token dekódolását végző függvény
   * @param token A token
   */
  private getDecodedAccesToken(token) {
    try {
      return this.jwtHelper.decodeToken(token);
    } catch (Error) {
      return null;
    }
  }

  /**
   * A session beállításáért felelős függvény.
   * Először beállítjuk a localStorage állapotát a
   * HttpResponse-ból kiszedett token alapján,
   * majd átadjuk az engedélyeket a Subjectnek.
   * @param response A válasz
   */
  private setSession(response: HttpResponse<any>) {
    const token = this.getDecodedAccesToken(
      response.headers.get(Authentication.TOKEN_NAME)
    );

    localStorage.setItem("username", token.sub);
    localStorage.setItem("authorities", token.authorities);
    localStorage.setItem("expires_at", token.exp);
    localStorage.setItem(
      "token",
      response.headers.get(Authentication.TOKEN_NAME)
    );

    this.loginStatus.next(this.getAuthorities());
  }

  /**
   * A kijelentkezést végrehajtó függvény.
   * Először visszaállítjuk a localStorage állapotát,
   * majd átadjuk az engedélyeket a Subjectnek.
   */
  logOut() {
    localStorage.removeItem("username");
    localStorage.removeItem("authorities");
    localStorage.removeItem("expires_at");
    localStorage.removeItem("token");

    this.loginStatus.next(this.getAuthorities());
  }

  /**
   * A függvény ami ellenőrzi hogy a felhasználó be van-e jelentkezve
   */
  isLoggedIn(): boolean {
    const token = localStorage.getItem("token");
    if (token) {
      return !this.jwtHelper.isTokenExpired(localStorage.getItem("token"));
    } else {
      return false;
    }
  }

  /**
   *  A függvény ami ellenőrzi hogy a felhasználó ki van-e jelentkezve
   */
  isLoggedOut(): boolean {
    return !this.isLoggedIn();
  }

  /**
   * Az engedélyek lekérdezését megvalósító függvény
   * Minden esetben szeretnénk ha a látogató rendelkeznek a 'ROLE_GUEST' szerepkörrel!
   */
  getAuthorities(): string[] {
    const authorities = localStorage.getItem("authorities");
    if (authorities) {
      return authorities.split(",").concat(Authorities.ROLE_GUEST);
    } else {
      return [Authorities.ROLE_GUEST];
    }
  }

  /**
   * Egy adott engedély meglétét ellenőrző függvény
   * @param authority Az engedély
   */
  hasAuthority(authority: string): boolean {
    const authorities = localStorage.getItem("authorities");
    if (authority == Authorities.ROLE_GUEST) return true;
    if (authorities != null && authorities.length == 1)
      return authorities == authority;
    else if (authorities != null)
      return authorities.split(",").indexOf(authority) != -1;
    else return false;
  }

  /**
   * A felhasználónév lekérdezését megvalósító függvény
   */
  getUsername(): string {
    return localStorage.getItem("username");
  }
}
