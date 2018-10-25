import { Component, OnInit } from '@angular/core';
import { Subject } from '../../../../shared/models/Subject';
import { SubjectsDataService } from "../../subjects.data.service";

@Component({
  selector: 'app-subject-table',
  templateUrl: './subject-table.component.html',
  styleUrls: ['./subject-table.component.css']
})
export class SubjectTableComponent implements OnInit {
  /*A tantárgyak*/
  subjects: Subject[];

  constructor(
    private subjectService: SubjectsDataService
  ) {
  }

  /**
   * Az inicializálásért felelős függvény
   * A tantárgyak betöltése
   */
  ngOnInit() {
    this.subjectService.getAll().subscribe(
      res => this.subjects = res
    )
  }

}