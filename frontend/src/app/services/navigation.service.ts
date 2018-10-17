import { Injectable } from "@angular/core";
import { MenuItem } from "../models/MenuItem";
import { Authorities } from "../config/authoritites.config";

/**
 * A menühöz tartozó service osztály
 */
@Injectable()
export class NavigationService {
  /**
   * A menü
   */
  menuItems: MenuItem[] = [
    new MenuItem("Simple", "Főoldal", "/dashboard", Authorities.ROLE_GUEST),

    new MenuItem(
      "Simple",
      "Naptár",
      "/reservationsCalendar",
      Authorities.ROLE_USER
    ),

    new MenuItem("Dropdown", "Foglalásaim", "", Authorities.ROLE_USER, [
      new MenuItem(
        "Simple",
        "Megjelenítés táblázatban",
        "/myReservations",
        Authorities.ROLE_USER
      )
    ]),

    new MenuItem("Dropdown", "Táblázatok", "", Authorities.ROLE_ADMIN, [
      new MenuItem(
        "Simple",
        "Foglalások",
        "/reservations",
        Authorities.ROLE_GUEST
      ),
      new MenuItem("Simple", "Felhasználók", "/users", Authorities.ROLE_ADMIN),
      new MenuItem(
        "Simple",
        "Tantermek",
        "/classrooms",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem("Simple", "Félévek", "/semesters", Authorities.ROLE_ADMIN),
      new MenuItem("Simple", "Tantárgyak", "/subjects", Authorities.ROLE_ADMIN),
      new MenuItem("Simple", "Épületek", "/buildings", Authorities.ROLE_ADMIN)
      /*       new MenuItem(
        "Simple",
        "Várakozó foglalások",
        "/pendingReservations",
        Authorities.ROLE_ADMIN
      ) */
    ]),

    new MenuItem("Dropdown", "Űrlapok", "", Authorities.ROLE_USER, [
      new MenuItem(
        "Simple",
        "Új egyszerű foglalás",
        "/addSimpleReservation",
        Authorities.ROLE_USER
      ),
      new MenuItem(
        "Simple",
        "Új szemeszterre vonatkozó foglalás",
        "/addSemesterReservation",
        Authorities.ROLE_USER
      ),
      new MenuItem(
        "Simple",
        "Új eseményre vonatkozó foglalás",
        "/addEventReservation",
        Authorities.ROLE_USER
      ),
      new MenuItem(
        "Simple",
        "Új szemeszter",
        "/addSemester",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új felhasználó",
        "/addUser",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új tanterem",
        "/addClassroom",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új tantárgy",
        "/addSubject",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új épület",
        "/addBuilding",
        Authorities.ROLE_ADMIN
      )
    ])

    /*     new MenuItem(
      "Simple",
      "Várakozó foglalások kezelése",
      "/managePendingReservations",
      Authorities.ROLE_ADMIN
    ) */
  ];

  constructor() {}

  /**
   * A menü lekérdezését megvalósító függvény
   */
  getMenu(authorities: string[]): MenuItem[] {
    return this.filterMenuItems(this.menuItems, authorities);
  }

  /**
   * A menü, engedélyeknek megfelelő, megjelenítését megvalósító függvény
   * @param menuItems A menü
   */
  filterMenuItems(menuItems: MenuItem[], authorities: string[]): MenuItem[] {
    return menuItems.filter(menuItem => {
      /**
       * Ha sima menüpontról van szó
       */
      if (menuItem.type == "Simple") {
        return this.hasAuthority(authorities, menuItem.requiredAuthority);
      } else if (menuItem.type == "Dropdown") {
        /**
         * Ha legördülő menüről van szó
         */
        if (this.hasAuthority(authorities, menuItem.requiredAuthority)) {
          menuItem.menuItems = this.filterMenuItems(
            menuItem.menuItems,
            authorities
          );
          return true;
        } else {
          return false;
        }
      }
    });
  }

  private hasAuthority(authorities: string[], authority: string): boolean {
    return authorities.indexOf(authority) == -1 ? false : true;
  }
}
