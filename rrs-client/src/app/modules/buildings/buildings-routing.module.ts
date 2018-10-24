import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';
import { BuildingTablePageComponent } from './pages/building-table-page/building-table-page.component';
import { AddBuildingPageComponent } from './pages/add-building-page/add-building-page.component';


const routes: Routes = [
  {
    path: "buildings",
    component: BuildingTablePageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addBuilding",
    component: AddBuildingPageComponent,
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