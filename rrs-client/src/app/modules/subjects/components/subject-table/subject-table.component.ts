import { Component, OnInit } from '@angular/core';
import { Subject } from '../../../../shared/models/Subject';
import { SubjectApiService } from '../../../../shared/services/api/subject.api.service';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-subject-table',
  templateUrl: './subject-table.component.html',
  styleUrls: ['./subject-table.component.css']
})
export class SubjectTableComponent implements OnInit {

  subjects: Subject[];

  constructor(
    private subjectService: SubjectApiService
  ) {
  }

  ngOnInit() {
    this.subjectService.getAll().subscribe(
      res => this.subjects = res
    )
  }

}