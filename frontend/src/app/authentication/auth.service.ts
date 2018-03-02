import { Injectable } from '@angular/core';
import { Config } from '../config/config';
import { UserService } from '../services/user.service';
import { Routes } from '../config/routes.config';
import { AccountCredentials } from '../models/AccountCredentials';
import { HttpResponse } from '@angular/common/http';

//Aktuális pillanat:
import * as moment from 'moment';
//Token dekódolás:
import * as jwt_decode from "jwt-decode";


@Injectable()
export class AuthService {

    constructor(
        private userService: UserService
    ) {

    }

    login(accountCredentials: AccountCredentials) {
        return this.userService.login(accountCredentials).subscribe(
            (response:HttpResponse<any>) =>  {
                this.setSession(response);
                console.log(this.getDecodedAccesToken(response.headers.get('Authorization')))
            }
        );
    }

    /*
        Token dekódolása:
    */
    private getDecodedAccesToken(token) {
        try{
            return jwt_decode(token);
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
        
        localStorage.setItem('user', token.sub);
        localStorage.setItem('authorities', token.authorities);
        localStorage.setItem('expires_at', token.exp);
        localStorage.setItem('token', response.headers.get('Authorization'))

        console.log(moment().isBefore(localStorage.getItem('expires_at')));
        console.log(JSON.parse(localStorage.getItem('expires_at')));
    }

    /*
        Kiejelentkezéskor töröljük a tokeneket:
    */
    logout() {
        localStorage.removeItem("user");
        localStorage.removeItem("authorities");
        localStorage.removeItem("expires_at");
        localStorage.removeItem("token");
    }

    public isLoggedIn() {
        return moment().isBefore(this.getExpiration());
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    getExpiration() {
        const expiration = localStorage.getItem("expires_at");
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }    

    //
    getToken() {
        return "";
    }
}
