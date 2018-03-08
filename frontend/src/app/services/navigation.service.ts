import { Injectable } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { MenuItem } from '../models/MenuItem';

@Injectable()
export class NavigationService {
    /*
        Menu:
    */
    menuItems: MenuItem[] = [
        new MenuItem("Simple", "Főoldal", "/dashboard", "ANY"),

        new MenuItem("Simple", "Foglalások", "/reservations", "ANY"),

        new MenuItem("Dropdown", "Foglalásaim", "", "ROLE_USER", [
            new MenuItem("Simple", "Táblázatos megjelenítés", "/myReservations", "ROLE_USER"),
            new MenuItem("Simple", "Részletes megjelenítés", "/myReservationsDetailed", "ROLE_USER")
        ]),

        new MenuItem("Dropdown", "Táblázatok", "", "ROLE_ADMIN", [
            new MenuItem("Simple", "Felhasználók", "/users", "ROLE_ADMIN"),
            new MenuItem("Simple", "Tantermek", "/classrooms", "ROLE_ADMIN"),
            new MenuItem("Simple", "Tantárgyak", "/subjects", "ROLE_ADMIN"),
            new MenuItem("Simple", "Épületek", "/buildings", "ROLE_ADMIN"),
            new MenuItem("Simple", "Jegyek", "/tickets", "ROLE_ADMIN")
        ]),

        new MenuItem("Dropdown", "Űrlapok", "", "ROLE_USER", [
            new MenuItem("Simple", "Új foglalás", "/addReservation", "ROLE_USER"),
            new MenuItem("Simple", "Új felhasználó", "/addUser", "ROLE_ADMIN"),
            new MenuItem("Simple", "Új tanterem", "/addClassroom", "ROLE_ADMIN"),
            new MenuItem("Simple", "Új tantárgy", "/addSubject", "ROLE_ADMIN"),
            new MenuItem("Simple", "Új épület", "/addBuilding", "ROLE_ADMIN")
        ])
    ];

    constructor(
        private authService: AuthService
    ) {}

    /*
        Menu lekérdezése:
    */
    getMenu(): MenuItem[] {
        return this.filterMenuItems(this.menuItems);
    }

    /*
        Menü szűrése az engedélyeknek megfelelően:
        //USER LOGIN --> ADMIN LOGIN --> BUGOS ŰRLAP MENU
    */
    filterMenuItems(menuItems: MenuItem[]): MenuItem[] {
        return menuItems.filter(
            menuItem => {
                //Ha sima menüpontról van szó:
                if (menuItem.type == "Simple") {
                    return this.authService.hasAuthority(menuItem.authorityRequired);
                }

                //Ha legördülő menüről van szó:
                else if (menuItem.type == "Dropdown") {
                    //Rendelkezünk-e a megfelelő engedélyekkel?
                    //-->igen:
                    if (this.authService.hasAuthority(menuItem.authorityRequired)) {
                        menuItem.menuItems = this.filterMenuItems(menuItem.menuItems);
                        return true;
                    }
                    //-->nem:
                    else {
                        return false;
                    }

                }
            }
        )
    }
}
