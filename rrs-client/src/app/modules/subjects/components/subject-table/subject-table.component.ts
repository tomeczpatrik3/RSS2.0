import { Component, OnInit } from "@angular/core";
import { Subject } from "../../../../shared/models/Subject";
import { SubjectsDataService } from "../../subjects.data.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-subject-table",
  templateUrl: "./subject-table.component.html",
  styleUrls: ["./subject-table.component.css"]
})
export class SubjectTableComponent implements OnInit {
  /*A tantárgyak*/
  subjects: Subject[];

  constructor(
    private subjectService: SubjectsDataService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A tantárgyak betöltése
   */
  ngOnInit() {
    this.subjectService.getAll().subscribe(res => (this.subjects = res));
  }

  /**
   * A megfelelő dialógus megjlenítéséért felelős függvény
   * @param id A tantárgy azonosítója
   */
  openPopup(id: number) {
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService
        .openFormDialog(
          "Tantárgy szerkesztése:",
          FormType.EDIT_SUBJECT_FORM,
          id,
          FormDialogComponent
        )
        .subscribe(result => {
          if (result == true) {
            this.refreshTable();
          }
        });
    }
  }

  /**
   * A táblázat frissítéséért felelős függvény
   */
  refreshTable(): void {
    this.subjectService.getAll().subscribe(res => (this.subjects = res));
  }
}
