import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuildingsRoutingModule } from './buildings-routing.module';
import { AddBuildingFormComponent } from './components/add-building-form/add-building-form.component';
import { BuildingTableComponent } from './components/building-table/building-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BuildingsDataService } from './buildings.data.service';
import { AddBuildingPageComponent } from './pages/add-building-page/add-building-page.component';
import { BuildingTablePageComponent } from './pages/building-table-page/building-table-page.component';
import { EditBuildingFormComponent } from './components/edit-building-form/edit-building-form.component';

@NgModule({
  imports: [
    CommonModule,
    BuildingsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddBuildingFormComponent,
    BuildingTableComponent,
    AddBuildingPageComponent,
    BuildingTablePageComponent,
    EditBuildingFormComponent
  ],
  providers:[
    BuildingsDataService
  ],
  exports: [
    EditBuildingFormComponent
  ]
})
export class BuildingsModule { }
