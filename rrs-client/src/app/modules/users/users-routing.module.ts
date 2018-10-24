import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginFormComponent } from "./components/login-form/login-form.component";
import { AddUserFormComponent } from "./components/add-user-form/add-user-form.component";
import { UserTableComponent } from "./components/user-table/user-table.component";
import { RoleGuard } from "../../shared/guards/role-guard.service";
import { Authorities } from "../../shared/config/authoritites.config";
import { CanDeactivateGuard } from "../../shared/guards/can-deactivate-guard.service";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { AddUserPageComponent } from "./pages/add-user-page/add-user-page.component";
import { UserPageComponent } from "./pages/user-page/user-page.component";

const routes: Routes = [
  {
    path: "login",
    component: LoginPageComponent
  },
  {
    path: "register",
    component: AddUserPageComponent
  },
  {
    path: "users",
    component: UserPageComponent,
    canActivate: [RoleGuard],
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
