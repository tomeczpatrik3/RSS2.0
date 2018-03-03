import { Injectable } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { MenuItem } from '../models/MenuItem';

@Injectable()
export class NavigationService {
    /*
        Menu:
    */
    menuItems: MenuItem[] = [
        new MenuItem("Főoldal", "/dashboard", "ROLE_USER"),
        new MenuItem("Bejelentkezés", "/login"),
    ];

    constructor(
        private authService: AuthService
    ) {}

    /*
        Menu lekérdezése:
        -- ha nem szükséges hozzá engedély: a menüpontot továbbadjuk
        -- ha szükséges hozzá engedély: megnézzük, hogy az aktuális user rendelkezik-e vele
    */
    getMenu(): MenuItem[] {
        return this.menuItems.filter(
            element => {
                return element.authorityRequired == "" ? true : this.authService.hasAuthority(element.authorityRequired)
           }
        )
    }  
}
