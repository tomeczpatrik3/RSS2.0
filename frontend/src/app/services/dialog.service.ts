import { Injectable } from "@angular/core";
import { MatDialog } from "@angular/material";
import { InfoDialogComponent } from "../components/dialogs/info-dialog/info-dialog.component";
import { Subject } from "rxjs/Subject";

/**
 * Az dialógusokhoz tartozó service osztály
 */
@Injectable()
export class DialogService {
  returnValue = new Subject<boolean>();

  constructor(private dialog: MatDialog) {}

  /**
   * Dialog megjelenítése, valamint adatok átadása
   * @param title_ A dialog címe
   * @param text_ A dialogban közölt üzenet
   * @param dialogComponent A dialog komponens
   */
  openDialog(title_: string, text_: string, dialogComponent) {
    let dialogRef = this.dialog.open(dialogComponent, {
      width: "600px",
      data: {
        title: title_,
        text: text_
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.returnValue.next(result == "Confirm" ? true : false);
    });
  }

  /**
   * Sortörések hozzáadása a szöveghez:
   * @param text A szöveg
   */
  addBr(text: string): string {
    return text.replace(new RegExp("\n", "g"), "<br />");
  }
}
