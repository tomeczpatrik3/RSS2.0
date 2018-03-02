import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../components/dialogs/info-dialog/info-dialog.component';

@Injectable()
export class DialogService {

  constructor(
    private dialog: MatDialog
  ) { }

  /*
    Dialog megjelenítése, valamint adatok átadása:
    title: a dialog címe
    text: a dialogban közölt üzenet

    Dialog bezárása után reseteljük a formot!
  */
  openDialog(title_: string, text_: string, dialogComponent) {
    let dialogRef = this.dialog.open(dialogComponent, {
      width: '600px',
      data: {
        title: title_,
        text: text_
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      //Bezáródás utáni cselekvés
    })
  }

}
