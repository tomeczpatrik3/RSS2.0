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

  subjects: Subject[];

  constructor(
    private subjectService: SubjectService
  ) {
  }

  ngOnInit() {
    this.subjectService.getAll().subscribe(
      res => this.subjects = res
    )
  }

}