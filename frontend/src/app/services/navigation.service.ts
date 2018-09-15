import { Injectable } from '@angular/core';
import { AuthService } from '../authentication/auth.service';
import { MenuItem } from '../models/MenuItem';
import { Authorities } from '../config/authoritites.config';

@Injectable()
export class NavigationService {
    /*
        Menu:
    */
    menuItems: MenuItem[] = [
        new MenuItem("Simple", "Főoldal", "/dashboard", Authorities.ROLE_GUEST),

        new MenuItem("Simple", "Foglalások", "/reservations", Authorities.ROLE_GUEST),

        new MenuItem("Simple", "Napi nézet", "/dailyView", Authorities.ROLE_ADMIN),

        new MenuItem("Dropdown", "Foglalásaim", "", Authorities.ROLE_USER, [
            new MenuItem("Simple", "Táblázatos megjelenítés", "/myReservations", Authorities.ROLE_USER),
            new MenuItem("Simple", "Részletes megjelenítés", "/myReservationsDetailed", Authorities.ROLE_USER)
        ]),

        new MenuItem("Dropdown", "Táblázatok", "", Authorities.ROLE_ADMIN, [
            new MenuItem("Simple", "Felhasználók", "/users", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Tantermek", "/classrooms", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Félévek", "/semesters", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Tantárgyak", "/subjects", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Épületek", "/buildings", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Várakozó foglalások", "/pendingReservations", Authorities.ROLE_ADMIN)
        ]),

        new MenuItem("Dropdown", "Űrlapok", "", Authorities.ROLE_USER, [
            new MenuItem("Simple", "Új foglalás", "/addReservation", Authorities.ROLE_USER),
            new MenuItem("Simple", "Új szemeszter", "/addSemester", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Új felhasználó", "/addUser", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Új tanterem", "/addClassroom", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Új tantárgy", "/addSubject", Authorities.ROLE_ADMIN),
            new MenuItem("Simple", "Új épület", "/addBuilding", Authorities.ROLE_ADMIN),
        ]),

        new MenuItem("Simple", "Várakozó foglalások kezelése", "/managePendingReservations", Authorities.ROLE_ADMIN),
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
