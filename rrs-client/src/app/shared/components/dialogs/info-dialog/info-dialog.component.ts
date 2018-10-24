import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef } from "@angular/material";
import { MAT_DIALOG_DATA } from "@angular/material";

@Component({
  selector: "app-info-dialog",
  templateUrl: "./info-dialog.component.html",
  styleUrls: ["./info-dialog.component.css"]
})
export class InfoDialogComponent implements OnInit {
  constructor(
    public thisDialogRef: MatDialogRef<InfoDialogComponent>,
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
