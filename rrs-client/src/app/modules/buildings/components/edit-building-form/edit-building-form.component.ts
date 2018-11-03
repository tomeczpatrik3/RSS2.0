import { Component, OnInit, Input, EventEmitter, Output } from "@angular/core";
import { Building } from "../../../../shared/models/Building";
import { BuildingsDataService } from "../../buildings.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";

@Component({
  selector: "app-edit-building-form",
  templateUrl: "./edit-building-form.component.html",
  styleUrls: ["./edit-building-form.component.css"]
})
export class EditBuildingFormComponent implements OnInit {
  /*Az épület azonosítója*/
  @Input()
  buildingID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*Az épület*/
  model: Building;

  constructor(
    private buildingService: BuildingsDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.buildingService.findById(this.buildingID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.buildingService
      .update(this.model.id, this.model)
      .subscribe(
        () => this.submitEvent.next(true),
        error =>
          this.dialogService.openDialog(
            "Épület szerkesztése:",
            TextUtils.addBreaks(error.error),
            InfoDialogComponent
          )
      );
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }
}
