import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Classroom } from "../../../../shared/models/Classroom";
import { ClassroomsDataService } from "../../classrooms.data.service";

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

  constructor(private classroomService: ClassroomsDataService) {}

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
      .subscribe(classroom => console.log(classroom));
    this.submitEvent.next(true);
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }
}
