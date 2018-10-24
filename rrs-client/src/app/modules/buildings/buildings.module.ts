import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuildingsRoutingModule } from './buildings-routing.module';
import { AddBuildingFormComponent } from './components/add-building-form/add-building-form.component';
import { BuildingTableComponent } from './components/building-table/building-table.component';
import { DialogService } from '../../shared/services/dialog.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BuildingsDataService } from './buildings.data.service';

@NgModule({
  imports: [
    CommonModule,
    BuildingsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddBuildingFormComponent,
    BuildingTableComponent
  ],
  providers:[
    DialogService,
    BuildingsDataService
  ]
})
export class BuildingsModule { }
