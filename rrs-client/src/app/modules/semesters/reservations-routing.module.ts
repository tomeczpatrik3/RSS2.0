import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SemesterTableComponent } from './components/semester-table/semester-table.component';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { AddSemesterFormComponent } from './components/add-semester-form/add-semester-form.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';


const routes: Routes = [
  {
    path: "/tables/semesters",
    component: SemesterTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "/forms/addSemester",
    component: AddSemesterFormComponent,
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