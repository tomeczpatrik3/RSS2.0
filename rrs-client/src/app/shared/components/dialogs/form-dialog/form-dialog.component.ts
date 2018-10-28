import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { FormType } from "../../../enums/FormType";

@Component({
  selector: "app-form-dialog",
  templateUrl: "./form-dialog.component.html",
  styleUrls: ["./form-dialog.component.css"]
})
export class FormDialogComponent implements OnInit {
  /**
   * A lehetséges/megjleníthető formok
   */
  types: FormType[] = [
    FormType.EDIT_CLASS_RESERVATION_FORM,
    FormType.EDIT_EVENT_RESERVATION_FORM,
    FormType.OBSERVE_CLASS_RESERVATION_FORM,
    FormType.OBSERVE_EVENT_RESERVATION_FORM,
    FormType.EDIT_BUILDING_FORM,
    FormType.EDIT_CLASSROOM_FORM,
    FormType.EDIT_SEMESTER_FORM,
    FormType.EDIT_SUBJECT_FORM,
    FormType.EDIT_USER_FORM
  ];

  constructor(
    public thisDialogRef: MatDialogRef<FormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {}

  onCloseConfirm() {
    this.thisDialogRef.close(true);
  }

  onCloseCancel() {
    this.thisDialogRef.close(false);
  }

  /**
   * A függvény, ami a beérkező logikai érték alapján eldönti
   * hogy hogyan zárja be a dialógust
   * @param event A beérkező esemény
   */
  onClose(event: boolean) {
    if (event) {
      this.onCloseConfirm();
    } else {
      this.onCloseCancel();
    }
  }
}
