import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Semester } from "../../../../shared/models/Semester";
import { SemesterDataService } from "../../semesters.data.service";
import { patterns } from "../../../../shared/utils/patterns";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { InfoDialogComponent } from "../../../../shared/components/dialogs/info-dialog/info-dialog.component";
import { DialogService } from "../../../../shared/services/dialog.service";

@Component({
  selector: "app-edit-semester-form",
  templateUrl: "./edit-semester-form.component.html",
  styleUrls: ["./edit-semester-form.component.css"]
})
export class EditSemesterFormComponent implements OnInit {
  /*A tanterem azonosítója*/
  @Input()
  semesterID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A szemeszter*/
  model: Semester;
  /*A szemeszter név pattern*/
  semesterNamePattern: string = patterns.semesterName;

  constructor(
    private semesterService: SemesterDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.semesterService.findById(this.semesterID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.semesterService
      .update(this.model.id, this.model)
      .subscribe(
        () => this.submitEvent.next(true),
        error =>
          this.dialogService.openDialog(
            "Szemeszter szerkesztése:",
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
