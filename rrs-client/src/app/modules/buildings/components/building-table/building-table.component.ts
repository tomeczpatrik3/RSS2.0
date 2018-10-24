import { Component, OnInit } from '@angular/core';
import { Building } from '../../../../shared/models/Building';
import { BuildingApiService } from '../../../../shared/services/api/building.api.service';

@Component({
  selector: 'app-building-table',
  templateUrl: './building-table.component.html',
  styleUrls: ['./building-table.component.css']
})
export class BuildingTableComponent implements OnInit {

  buildings: Building[];

  constructor(
    private buildingService: BuildingApiService
  ) {
  }

  ngOnInit() {
    this.buildingService.getAll().subscribe(
      res => this.buildings = res
    )
  }

}