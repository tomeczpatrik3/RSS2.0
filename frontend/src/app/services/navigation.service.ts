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
        new MenuItem("Felhasználók", "/users", "ROLE_USER"),
        new MenuItem("Tantermek", "/classrooms", "ROLE_USER"),
        new MenuItem("Foglalások", "/reservations", "ROLE_USER"),
        new MenuItem("Tantárgyak", "/subjects", "ROLE_USER"),
        new MenuItem("Új felhasználó", "/addUser", "ROLE_ADMIN"),
        new MenuItem("Új tanterem", "/addClassroom", "ROLE_ADMIN"),
        new MenuItem("Új foglalás", "/addReservation", "ROLE_ADMIN"),
        new MenuItem("Új tantárgy", "/addSubject", "ROLE_ADMIN"),
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
