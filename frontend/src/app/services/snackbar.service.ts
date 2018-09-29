import { Injectable, Input } from "@angular/core";
import { MatSnackBar } from "@angular/material";

/**
 * A Snackbarhoz tartozó szervíz osztály
 */
@Injectable()
export class SnackbarService {
  constructor(private snackBar: MatSnackBar) {}

  /**
   * A Snackbart megjelenítő függvény
   * @param message Az üzenet
   * @param action A cselekvés
   * @param duration Az időtartam
   */
  open(message: string, action: string, duration?: number) {
    this.snackBar.open(message, action, {
      duration: duration == null ? 3000 : duration * 1000
    });
  }
}
