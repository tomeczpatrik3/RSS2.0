import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubjectTableComponent } from './components/subject-table/subject-table.component';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { AddSubjectFormComponent } from './components/add-subject-form/add-subject-form.component';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';


const routes: Routes = [
  {
    path: "tables/subjects",
    component: SubjectTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "forms/addSubject",
    component: AddSubjectFormComponent,
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
export class SubjectsRoutingModule { }