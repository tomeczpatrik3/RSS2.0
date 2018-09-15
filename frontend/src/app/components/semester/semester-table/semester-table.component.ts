import { Component, OnInit } from '@angular/core';
import { Semester } from '../../../models/Semester';
import { SemesterService } from '../../../services/semester.service';

@Component({
  selector: 'app-semester-table',
  templateUrl: './semester-table.component.html',
  styleUrls: ['./semester-table.component.css']
})
export class SemesterTableComponent implements OnInit {

  semesters: Semester[];

  constructor(
    private semesterService: SemesterService
  ) {
  }

  ngOnInit() {
    this.semesterService.getAll().subscribe(
      res => this.semesters = res
    )
  }
}
