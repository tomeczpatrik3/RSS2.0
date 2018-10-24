import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginFormComponent } from "./components/login-form/login-form.component";
import { AddUserFormComponent } from "./components/add-user-form/add-user-form.component";
import { UserTableComponent } from "./components/user-table/user-table.component";
import { RoleGuard } from "../../shared/guards/role-guard.service";
import { Authorities } from "../../shared/config/authoritites.config";
import { CanDeactivateGuard } from "../../shared/guards/can-deactivate-guard.service";

const routes: Routes = [
  {
    path: "/login",
    component: LoginFormComponent
  },
  {
    path: "/register",
    component: AddUserFormComponent
  },
  {
    path: "/tables/users",
    component: UserTableComponent,
    canActivate: [RoleGuard],
    data: {
      authority: Authorities.ROLE_ADMIN
    }
  },
  {
    path: "/forms/addUser",
    component: AddUserFormComponent,
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
export class UsersRoutingModule {}
