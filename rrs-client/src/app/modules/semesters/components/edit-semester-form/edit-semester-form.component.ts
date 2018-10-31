import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Semester } from "../../../../shared/models/Semester";
import { SemesterDataService } from "../../semesters.data.service";
import { patterns } from "../../../../shared/utils/patterns";

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

  constructor(private semesterService: SemesterDataService) {}

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
      .subscribe(semester => this.submitEvent.next(true));
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }
}
