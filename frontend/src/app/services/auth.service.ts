import { Injectable } from '@angular/core';
import { Config } from '../config/config';

@Injectable()
export class AuthService {
    login(token: string) : void {
        localStorage.setItem(Config.TOKEN, token);
    }

    logout(): void {
        localStorage.removeItem(Config.TOKEN);
    }

    isLoggedIn() : boolean {
        return this.getToken() != null;
    }

    getToken() : string {
        return localStorage.getItem(Config.TOKEN);
    }
}

