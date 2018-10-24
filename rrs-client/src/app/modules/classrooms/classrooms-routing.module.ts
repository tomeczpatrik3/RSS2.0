import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';
import { ClassroomTablePageComponent } from './pages/classroom-table-page/classroom-table-page.component';
import { AddClassroomPageComponent } from './pages/add-classroom-page/add-classroom-page.component';

const routes: Routes = [
  {
    path: "classrooms",
    component: ClassroomTablePageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addClassroom",
    component: AddClassroomPageComponent,
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
export class ClassroomsRoutingModule { }