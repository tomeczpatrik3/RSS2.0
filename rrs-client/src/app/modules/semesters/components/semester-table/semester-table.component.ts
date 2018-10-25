import { Component, OnInit } from '@angular/core';
import { Semester } from '../../../../shared/models/Semester';
import { SemesterDataService } from "../../semesters.data.service";

@Component({
  selector: 'app-semester-table',
  templateUrl: './semester-table.component.html',
  styleUrls: ['./semester-table.component.css']
})
export class SemesterTableComponent implements OnInit {

  /*A szemeszterek*/
  semesters: Semester[];

  constructor(
    private semesterService: SemesterDataService
  ) {
  }

  /**
   * Az inicializálásért felelős függvény
   * A szemeszterek betöltése
   */
  ngOnInit() {
    this.semesterService.getAll().subscribe(
      res => this.semesters = res
    )
  }
}
