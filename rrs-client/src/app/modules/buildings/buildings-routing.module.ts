import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BuildingTableComponent } from './components/building-table/building-table.component';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { AddBuildingFormComponent } from './components/add-building-form/add-building-form.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';


const routes: Routes = [
  {
    path: "tables/buildings",
    component: BuildingTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "forms/addBuilding",
    component: AddBuildingFormComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BuildingsRoutingModule { }