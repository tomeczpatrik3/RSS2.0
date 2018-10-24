import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';
import { SemesterPageComponent } from './pages/semester-page/semester-page.component';
import { AddSemesterPageComponent } from './pages/add-semester-page/add-semester-page.component';


const routes: Routes = [
  {
    path: "semesters",
    component: SemesterPageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addSemester",
    component: AddSemesterPageComponent,
    canActivate: [RoleGuard],
    canDeactivate: [CanDeactivateGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SemestersRoutingModule { }