import { Component, OnInit, Output, Input, EventEmitter } from "@angular/core";
import { Subject } from "../../../../shared/models/Subject";
import { SubjectsDataService } from "../../subjects.data.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-edit-subject-form",
  templateUrl: "./edit-subject-form.component.html",
  styleUrls: ["./edit-subject-form.component.css"]
})
export class EditSubjectFormComponent implements OnInit {
  /*A tantárgy azonosítója*/
  @Input()
  subjectID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A tantárgy*/
  model: Subject;

  constructor(
    private subjectService: SubjectsDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   */
  ngOnInit() {
    this.subjectService.findById(this.subjectID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.subjectService
      .update(this.model.id, this.model)
      .subscribe(
        () => this.submitEvent.next(true),
        error =>
          this.dialogService.openDialog(
            "Tantárgy szerkesztése:",
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
