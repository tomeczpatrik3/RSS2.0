import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-edit-event-reservation-dialog',
  templateUrl: './edit-event-reservation-dialog.component.html',
  styleUrls: ['./edit-event-reservation-dialog.component.css']
})
export class EditEventReservationDialogComponent implements OnInit {

  constructor(
    public thisDialogRef: MatDialogRef<EditEventReservationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string,
  ){}

  ngOnInit() {}

  onCloseConfirm() {
    this.thisDialogRef.close(true);
  }

  onCloseCancel() {
    this.thisDialogRef.close(false);
  }

}
