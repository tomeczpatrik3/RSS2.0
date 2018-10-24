import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuildingsRoutingModule } from './buildings-routing.module';
import { AddBuildingFormComponent } from './components/add-building-form/add-building-form.component';
import { BuildingTableComponent } from './components/building-table/building-table.component';
import { DialogService } from '../../shared/services/dialog.service';
import { BuildingApiService } from '../../shared/services/api/building.api.service';

@NgModule({
  imports: [
    CommonModule,
    BuildingsRoutingModule
  ],
  declarations: [
    AddBuildingFormComponent,
    BuildingTableComponent
  ],
  providers:[
    DialogService,
    BuildingApiService
  ]
})
export class BuildingsModule { }
