import { Component, OnInit } from '@angular/core';
import { Building } from '../../../models/Building';
import { BuildingService } from '../../../services/building.service';

@Component({
  selector: 'app-building-table',
  templateUrl: './building-table.component.html',
  styleUrls: ['./building-table.component.css']
})
export class BuildingTableComponent implements OnInit {

  buildings: Building[];

  constructor(
    private buildingService: BuildingService
  ) {
  }

  ngOnInit() {
    this.buildingService.getAll().subscribe(
      res => this.buildings = res
    )
  }

}