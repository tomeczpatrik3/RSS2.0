import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClassroomTableComponent } from '../classrooms/components/classroom-table/classroom-table.component';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { AddClassroomFormComponent } from '../classrooms/components/add-classroom-form/add-classroom-form.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';

const routes: Routes = [
  {
    path: "/tables/classrooms",
    component: ClassroomTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "/forms/addClassroom",
    component: AddClassroomFormComponent,
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