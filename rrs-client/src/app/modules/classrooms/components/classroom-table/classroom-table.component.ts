import { Component, OnInit } from '@angular/core';
import { Classroom } from '../../../../shared/models/Classroom';
import { ClassroomsDataService } from "../../classrooms.data.service";
import { DialogService } from '../../../../shared/services/dialog.service';
import { InfoDialogComponent } from '../../../../shared/components/dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-classroom-table',
  templateUrl: './classroom-table.component.html',
  styleUrls: ['./classroom-table.component.css']
})
export class ClassroomTableComponent implements OnInit {
  /*A tantermek*/
  rooms: Classroom[];

  constructor(
    private classroomService: ClassroomsDataService,
    private dialogService: DialogService
  ) {
  }

  /**
   * Az inicializálásért felelős függvény
   * A tantermek betöltése
   */
  ngOnInit() {
    this.classroomService.getAll().subscribe(
      res => this.rooms = res
    )
  }
}
