import { Component, OnInit } from '@angular/core';
import { Classroom } from '../../../models/Classroom';
import { ClassroomService } from '../../../services/classroom.service';
import { Observable } from 'rxjs/Observable';
import { DataSource } from '@angular/cdk/collections';

@Component({
  selector: 'app-classroom-table',
  templateUrl: './classroom-table.component.html',
  styleUrls: ['./classroom-table.component.css']
})
export class ClassroomTableComponent implements OnInit {

  classroomData = new ClassroomDataSource(this.classroomService);
  columnsToDisplay = ['building', 'roomName', 'pc', 'projector', 'chairs'];

  constructor(
    private classroomService: ClassroomService
  ) {
  }

  ngOnInit() {
  }

  clicked(target) {
    console.log(target);
    console.log(target.innerText);
  }

}

export class ClassroomDataSource extends DataSource<any> {

  constructor (
    private classroomService: ClassroomService
  ) {
    super();
  }

  connect(): Observable<Classroom[]> {
    return this.classroomService.getAll();
  }

  disconnect() {}
}
