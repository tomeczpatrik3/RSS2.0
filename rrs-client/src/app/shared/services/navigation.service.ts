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
    /**
     * Főoldal:
     */
    new MenuItem("Simple", "Főoldal", "/dashboard", Authorities.ROLE_GUEST),

    /**
     * Foglalások:
     */
    new MenuItem("Dropdown", "Foglalások", "", Authorities.ROLE_GUEST, [
      new MenuItem(
        "Simple",
        "Megjelenítés táblázatban",
        "/events/events",
        Authorities.ROLE_GUEST
      ),
      new MenuItem(
        "Simple",
        "Megjelenítés naptárban",
        "/events/calendar",
        Authorities.ROLE_GUEST
      ),
      new MenuItem(
        "Simple",
        "Várakozó foglalások",
        "/reservations/pendingReservations",
        Authorities.ROLE_ADMIN
      )
    ]),

    /**
     * Saját foglalások:
     */
    new MenuItem("Dropdown", "Foglalásaim", "", Authorities.ROLE_USER, [
      new MenuItem(
        "Simple",
        "Megjelenítés táblázatban",
        "/reservations/myReservations",
        Authorities.ROLE_USER
      )
    ]),

    /**
     * Táblázatok:
     */
    new MenuItem("Dropdown", "Táblázatok", "", Authorities.ROLE_ADMIN, [
      new MenuItem(
        "Simple",
        "Felhasználók",
        "/users/users",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Tantermek",
        "/classrooms/classrooms",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Félévek",
        "/semesters/semesters",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Tantárgyak",
        "/subjects/subjects",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Épületek",
        "/buildings/buildings",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Tantárgy foglalások",
        "/reservations/classReservations",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Esemény foglalások",
        "/reservations/eventReservations",
        Authorities.ROLE_ADMIN
      )
    ]),

    /**
     * Űrlapok:
     */
    new MenuItem("Dropdown", "Űrlapok", "", Authorities.ROLE_USER, [
      new MenuItem(
        "Simple",
        "Új egyszerű foglalás",
        "/reservations/addSimpleReservation",
        Authorities.ROLE_USER
      ),
      new MenuItem(
        "Simple",
        "Új szemeszterre vonatkozó foglalás",
        "/reservations/addSemesterReservation",
        Authorities.ROLE_USER
      ),
      new MenuItem(
        "Simple",
        "Új eseményre vonatkozó foglalás",
        "/reservations/addEventReservation",
        Authorities.ROLE_USER
      ),
      new MenuItem(
        "Simple",
        "Új szemeszter",
        "/semesters/addSemester",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új felhasználó",
        "/users/register",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új tanterem",
        "/classrooms/addClassroom",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új tantárgy",
        "/subjects/addSubject",
        Authorities.ROLE_ADMIN
      ),
      new MenuItem(
        "Simple",
        "Új épület",
        "/buildings/addBuilding",
        Authorities.ROLE_ADMIN
      )
    ])
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

  /**
   * A függvény, ami visszaadja, hogy rendelkezünk-e az adott engedéllyel
   * @param authorities Az engedélyek egy listában
   * @param authority Az engedély
   */
  private hasAuthority(authorities: string[], authority: string): boolean {
    return authorities.indexOf(authority) == -1 ? false : true;
  }
}
