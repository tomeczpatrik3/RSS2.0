import { Component, OnInit } from '@angular/core';
import { Semester } from '../../../../shared/models/Semester';
import { SemesterDataService } from "../../semesters.data.service";

@Component({
  selector: 'app-semester-table',
  templateUrl: './semester-table.component.html',
  styleUrls: ['./semester-table.component.css']
})
export class SemesterTableComponent implements OnInit {

  semesters: Semester[];

  constructor(
    private semesterService: SemesterDataService
  ) {
  }

  ngOnInit() {
    this.semesterService.getAll().subscribe(
      res => this.semesters = res
    )
  }
}
