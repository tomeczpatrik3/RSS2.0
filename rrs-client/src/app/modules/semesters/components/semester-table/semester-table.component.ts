import { Component, OnInit } from "@angular/core";
import { Semester } from "../../../../shared/models/Semester";
import { SemesterDataService } from "../../semesters.data.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-semester-table",
  templateUrl: "./semester-table.component.html",
  styleUrls: ["./semester-table.component.css"]
})
export class SemesterTableComponent implements OnInit {
  /*A szemeszterek*/
  semesters: Semester[];

  constructor(
    private semesterService: SemesterDataService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A szemeszterek betöltése
   */
  ngOnInit() {
    this.semesterService.getAll().subscribe(res => (this.semesters = res));
  }

  /**
   * A megfelelő dialógus megjlenítéséért felelős függvény
   * @param id A szemeszter azonosítója
   */
  openPopup(id: number) {
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService
        .openFormDialog(
          "Szemeszter szerkesztése:",
          FormType.EDIT_SEMESTER_FORM,
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
    this.semesterService.getAll().subscribe(res => (this.semesters = res));
  }
}
