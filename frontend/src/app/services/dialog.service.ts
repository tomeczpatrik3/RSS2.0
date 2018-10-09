import { Injectable } from "@angular/core";
import { MatDialog } from "@angular/material";
import { InfoDialogComponent } from "../components/dialogs/info-dialog/info-dialog.component";
import { Observable } from "rxjs";

/**
 * Az dialógusokhoz tartozó service osztály
 */
@Injectable()
export class DialogService {
  constructor(private dialog: MatDialog) {}

  /**
   * Dialog megjelenítése, valamint adatok átadása
   * @param title_ A dialog címe
   * @param text_ A dialogban közölt üzenet
   * @param dialogComponent A dialog komponens
   */
  openDialog(
    title_: string,
    text_: string,
    dialogComponent
  ): Observable<boolean> {
    let dialogRef = this.dialog.open(dialogComponent, {
      width: "600px",
      data: {
        title: title_,
        text: text_
      }
    });

    return dialogRef.afterClosed();
  }

  /**
   * Sortörések hozzáadása a szöveghez:
   * @param text A szöveg
   */
  addBr(text: string): string {
    return text.replace(new RegExp("\n", "g"), "<br />");
  }
}
