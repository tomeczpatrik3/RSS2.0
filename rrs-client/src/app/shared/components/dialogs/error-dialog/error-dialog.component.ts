import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef } from "@angular/material";
import { MAT_DIALOG_DATA } from "@angular/material";

@Component({
  selector: "app-error-dialog",
  templateUrl: "./error-dialog.component.html",
  styleUrls: ["./error-dialog.component.css"]
})
export class ErrorDialogComponent implements OnInit {
  constructor(
    public thisDialogRef: MatDialogRef<ErrorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onCloseConfirm() {
    this.thisDialogRef.close(true);
  }

  onCloseCancel() {
    this.thisDialogRef.close(false);
  }
}
