import { Injectable } from "@angular/core";
import { MatDialog } from "@angular/material";
import { Observable } from "rxjs";

/**
 * Az dialógusokhoz tartozó service osztály
 */
@Injectable()
export class DialogService {
  constructor(private dialog: MatDialog) {}

  /**
   * A dialógusok megjelenítésére szolgáló függvény
   * @param title_ A cím
   * @param text_ Az üzenet
   * @param dialogComponent A dialógus komponens
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
   * A formokat tartalmazó dialógusok megjelenítésére szolgáló függvény
   * @param title_ A cím
   * @param type_  A típus
   * @param id_ Az entitás azonosítója
   * @param dialogComponent A dialógus komponens
   */
  openFormDialog(
    title_: string,
    type_: string,
    id_: number,
    dialogComponent
  ): Observable<boolean> {
    let dialogRef = this.dialog.open(dialogComponent, {
      width: "600px",
      data: {
        title: title_,
        text: "",
        id: id_,
        type: type_
      }
    });
    return dialogRef.afterClosed();
  }
}
