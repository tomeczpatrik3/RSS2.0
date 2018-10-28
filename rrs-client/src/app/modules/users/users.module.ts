import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { UsersRoutingModule } from "./users-routing.module";
import { AddUserFormComponent } from "./components/add-user-form/add-user-form.component";
import { LoginFormComponent } from "./components/login-form/login-form.component";
import { UserTableComponent } from "./components/user-table/user-table.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UsersDataService } from "./users.data.service";
import { AddUserPageComponent } from "./pages/add-user-page/add-user-page.component";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { UserPageComponent } from "./pages/user-page/user-page.component";
import { EditUserFormComponent } from "./components/edit-user-form/edit-user-form.component";

@NgModule({
  imports: [CommonModule, UsersRoutingModule, FormsModule, ReactiveFormsModule],
  declarations: [
    AddUserFormComponent,
    LoginFormComponent,
    UserTableComponent,
    AddUserPageComponent,
    LoginPageComponent,
    UserPageComponent,
    EditUserFormComponent
  ],
  providers: [UsersDataService],
  exports: [EditUserFormComponent]
})
export class UsersModule {}
