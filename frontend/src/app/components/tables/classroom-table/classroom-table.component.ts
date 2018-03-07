import { Component, OnInit } from '@angular/core';
import { Classroom } from '../../../models/Classroom';
import { ClassroomService } from '../../../services/classroom.service';
import { Observable } from 'rxjs/Observable';
import { DataSource } from '@angular/cdk/collections';
import { BuildingService } from '../../../services/building.service';
import { DialogService } from '../../../services/dialog.service';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-classroom-table',
  templateUrl: './classroom-table.component.html',
  styleUrls: ['./classroom-table.component.css']
})
export class ClassroomTableComponent implements OnInit {

  rooms: Classroom[];
  selectedRoom: Classroom[];

  constructor(
    private classroomService: ClassroomService,
    private dialogService: DialogService
  ) {
  }

  ngOnInit() {
    this.classroomService.getAll().subscribe(
      res => this.rooms = res
    )
  }

  convertToString(value: boolean): string {
    return value ? 'Van' : 'Nincs';
  }

  onSelect(room) {
    this.selectedRoom = room;
    this.dialogService.openDialog("Tanterem adatai:", room, InfoDialogComponent);
  }

}
