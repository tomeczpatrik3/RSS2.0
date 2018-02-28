import { Component, OnInit } from '@angular/core';
import { Subject } from '../../../models/Subject';
import { SubjectService } from '../../../services/subject.service';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-subject-table',
  templateUrl: './subject-table.component.html',
  styleUrls: ['./subject-table.component.css']
})
export class SubjectTableComponent implements OnInit {

  subjectData = new SubjectDataSource(this.subjectService);
  columnsToDisplay = ['name'];

  constructor(
    private subjectService: SubjectService
  ) {
  }

  ngOnInit() {
  }

}

export class SubjectDataSource extends DataSource<any> {

  constructor (
    private subjectService: SubjectService
  ) {
    super();
  }

  connect(): Observable<Subject[]> {
    return this.subjectService.getAll();
  }

  disconnect() {}
}
