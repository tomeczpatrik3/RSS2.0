import { Injectable } from "@angular/core";
import {
  CanDeactivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot
} from "@angular/router";
import { Observable } from "rxjs/Observable";

/**
 * CanComponentDeactivate interfész
 */
export interface CanComponentDeactivate {
  /**
   * A canDeactivate() függvény, melynek visszatérési értéke Observable, Promise vagy logikai érték
   */
  canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

/**
 * CanDeactivateGuard osztály
 */
@Injectable()
export class CanDeactivateGuard
  implements CanDeactivate<CanComponentDeactivate> {
/**
 * A canDeactivate() függvény
 * Ha a komponens implementálta a CanComponentDeactivate interfészt, 
 * akkor a canDeactivate() függvény visszatérési értéke alapján döntjük el,
 * hogy az oldal elhagyható-e. Egyéb esetben elhagyható.
 * @param component A komponens
 * @param route 
 * @param state 
 */
  canDeactivate(
    component: CanComponentDeactivate,
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ) {
    let url: string = state.url;
    console.log("Url: " + url);

    return component.canDeactivate ? component.canDeactivate() : true;
  }
}
