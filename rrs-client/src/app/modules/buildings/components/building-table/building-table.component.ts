import { Component, OnInit } from '@angular/core';
import { Building } from '../../../../shared/models/Building';
import { BuildingsDataService } from "../../buildings.data.service";

@Component({
  selector: 'app-building-table',
  templateUrl: './building-table.component.html',
  styleUrls: ['./building-table.component.css']
})
export class BuildingTableComponent implements OnInit {
  /*Az épületek*/
  buildings: Building[];

  constructor(
    private buildingService: BuildingsDataService
  ) {
  }

  /**
   * Az inicializálásért felelős függvény
   * Az épületek betöltése
   */
  ngOnInit() {
    this.buildingService.getAll().subscribe(
      res => this.buildings = res
    )
  }

}