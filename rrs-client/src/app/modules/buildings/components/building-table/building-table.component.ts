import { Component, OnInit } from "@angular/core";
import { Building } from "../../../../shared/models/Building";
import { BuildingsDataService } from "../../buildings.data.service";
import { AuthService } from "../../../../shared/services/auth.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { Authorities } from "../../../../shared/config/authoritites.config";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";
import { FormType } from "../../../../shared/enums/FormType";

@Component({
  selector: "app-building-table",
  templateUrl: "./building-table.component.html",
  styleUrls: ["./building-table.component.css"]
})
export class BuildingTableComponent implements OnInit {
  /*Az épületek*/
  buildings: Building[];

  constructor(
    private buildingService: BuildingsDataService,
    private authService: AuthService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * Az épületek betöltése
   */
  ngOnInit() {
    this.buildingService.getAll().subscribe(res => (this.buildings = res));
  }

  /**
   * A megfelelő dialógus megjlenítéséért felelős függvény
   * @param id Az épület azonosítója
   */
  openPopup(id: number) {
    if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
      this.dialogService
        .openFormDialog(
          "Épület szerkesztése:",
          FormType.EDIT_BUILDING_FORM,
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
    this.buildingService.getAll().subscribe(res => (this.buildings = res));
  }
}
