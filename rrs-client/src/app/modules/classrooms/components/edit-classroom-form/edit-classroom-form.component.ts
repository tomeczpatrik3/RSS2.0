import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Classroom } from "../../../../shared/models/Classroom";
import { ClassroomsDataService } from "../../classrooms.data.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-edit-classroom-form",
  templateUrl: "./edit-classroom-form.component.html",
  styleUrls: ["./edit-classroom-form.component.css"]
})
export class EditClassroomFormComponent implements OnInit {
  /*A tanterem azonosítója*/
  @Input()
  classroomID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*Az épület*/
  model: Classroom;

  constructor(
    private classroomService: ClassroomsDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.classroomService.findById(this.classroomID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.classroomService
      .update(this.model.id, this.model)
      .subscribe(
        () => this.submitEvent.next(true),
        error =>
          this.dialogService.openDialog(
            "Tanterem szerkesztése:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
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
