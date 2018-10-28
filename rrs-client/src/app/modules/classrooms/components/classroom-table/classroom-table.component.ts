import { Component, OnInit } from "@angular/core";
import { Classroom } from "../../../../shared/models/Classroom";
import { ClassroomsDataService } from "../../classrooms.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { AuthService } from "../../../../shared/services/auth.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-classroom-table",
  templateUrl: "./classroom-table.component.html",
  styleUrls: ["./classroom-table.component.css"]
})
export class ClassroomTableComponent implements OnInit {
  /*A tantermek*/
  rooms: Classroom[];

  constructor(
    private classroomService: ClassroomsDataService,
    private dialogService: DialogService,
    private authService: AuthService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A tantermek betöltése
   */
  ngOnInit() {
    this.classroomService.getAll().subscribe(res => (this.rooms = res));
  }

  /**
   * A megfelelő dialógus megjlenítéséért felelős függvény
   * @param id A tanterem azonosítója
   */
  openPopup(id: number) {
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService
        .openFormDialog(
          "Tanterem szerkesztése:",
          FormType.EDIT_CLASSROOM_FORM,
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
    this.classroomService.getAll().subscribe(res => (this.rooms = res));
  }
}
