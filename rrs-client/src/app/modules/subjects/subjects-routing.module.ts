import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from '../../shared/guards/role-guard.service';
import { Authorities } from '../../shared/config/authoritites.config';
import { CanDeactivateGuard } from '../../shared/guards/can-deactivate-guard.service';
import { SubjectPageComponent } from './pages/subject-page/subject-page.component';
import { AddSubjectPageComponent } from './pages/add-subject-page/add-subject-page.component';


const routes: Routes = [
  {
    path: "subjects",
    component: SubjectPageComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "addSubject",
    component: AddSubjectPageComponent,
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