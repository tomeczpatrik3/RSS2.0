import { Injectable } from '@angular/core';
import { Config } from '../config/config';
import { UserService } from '../services/user.service';
import { Routes } from '../config/routes.config';
import { AccountCredentials } from '../models/AccountCredentials';
import { HttpResponse } from '@angular/common/http';

//JWT Helper:
import { JwtHelperService } from '@auth0/angular-jwt';

import { Subject } from 'rxjs/Subject';

@Injectable()
export class AuthService {
    //Subject létrehozása a NavBar számára:
    loginStatus = new Subject<boolean>();

    constructor(
        private userService: UserService,
        private jwtHelper: JwtHelperService
    ) {

    }

    login(accountCredentials: AccountCredentials) {
        return this.userService.login(accountCredentials).subscribe(
            (response:HttpResponse<any>) =>  {
                this.setSession(response);
            }
        );
    }

    /*
        Token dekódolása:
    */
    private getDecodedAccesToken(token) {
        try{
            return this.jwtHelper.decodeToken(token);
        }
        catch(Error){
            return null;
        }        
    }
    
    /*
        Session adatok tárolása:
    */
    private setSession(response: HttpResponse<any>) {
        const token = this.getDecodedAccesToken(response.headers.get('Authorization'));
        
        localStorage.setItem('username', token.sub);
        localStorage.setItem('authorities', token.authorities);
        localStorage.setItem('expires_at', token.exp);
        localStorage.setItem('token', response.headers.get('Authorization'));

        //Belépett a felhasználó:
        this.loginStatus.next(true);
    }

    /*
        Kiejelentkezéskor töröljük a tokeneket:
    */
    logout() {
        localStorage.removeItem("username");
        localStorage.removeItem("authorities");
        localStorage.removeItem("expires_at");
        localStorage.removeItem("token");

        //Kilépett a felhasználó:
        this.loginStatus.next(false);
    }

    /*
        
    */
    isLoggedIn() {
        const token = localStorage.getItem('token');
        if (token) {
            return !this.jwtHelper.isTokenExpired(localStorage.getItem('token'));
        }
        else {
            return false;
        }
        
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    getAuthorities(): string[] {
        return localStorage.getItem('authorities').split(',');
    }

    /*
        Speciális engedély meglétének lekérdezése:
    */
    hasAuthority(authority: string): boolean {
        const authorities = localStorage.getItem('authorities');
        if (authority == 'ANY')
            return true;
        if (authorities != null && authorities.length == 1)
            return (authorities == authority);
        else if (authorities != null)
            return (authorities.split(',').indexOf(authority) != -1);
        else if (authority == "")
            return true;
        else
            return false;
    }

    /*
        Felhasználónév lekérdezése:
    */
    getUsername(): string {
        return localStorage.getItem('username');
    }
}
