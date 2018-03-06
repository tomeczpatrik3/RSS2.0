import { Injectable } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { MenuItem } from '../models/MenuItem';

@Injectable()
export class NavigationService {
    /*
        Menu:
    */
    menuItems: MenuItem[] = [
        new MenuItem("Simple", "Főoldal", "/dashboard", "ROLE_USER"),

        new MenuItem("Dropwdown", "Foglalások", "/reservations", "ANY"),

        new MenuItem("Simple", "Felhasználók", "/users", "ROLE_ADMIN"),
        new MenuItem("Simple", "Tantermek", "/classrooms", "ROLE_ADMIN"),
        new MenuItem("Simple", "Tantárgyak", "/subjects", "ROLE_ADMIN"),
        new MenuItem("Simple", "Új felhasználó", "/addUser", "ROLE_ADMIN"),
        new MenuItem("Simple", "Új tanterem", "/addClassroom", "ROLE_ADMIN"),
        new MenuItem("Simple", "Új foglalás", "/addReservation", "ROLE_ADMIN"),
        new MenuItem("Simple", "Új tantárgy", "/addSubject", "ROLE_ADMIN"),
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
                return this.authService.hasAuthority(element.authorityRequired)
           }
        )
    }  
}
