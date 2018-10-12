import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { FormType } from "../../../enums/FormType";

@Component({
  selector: "app-edit-dialog",
  templateUrl: "./edit-dialog.component.html",
  styleUrls: ["./edit-dialog.component.css"]
})
export class EditDialogComponent implements OnInit {
  types: FormType[] = [
    FormType.EDIT_CLASS_RESERVATION_FORM,
    FormType.EDIT_EVENT_RESERVATION_FORM
  ];

  constructor(
    public thisDialogRef: MatDialogRef<EditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) {}

  ngOnInit() {}

  onCloseConfirm() {
    this.thisDialogRef.close(true);
  }

  onCloseCancel() {
    this.thisDialogRef.close(false);
  }

  onClose(event: boolean) {
    if (event) {
      this.onCloseConfirm();
    } else {
      this.onCloseCancel();
    }
  }
}
