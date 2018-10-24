import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersRoutingModule } from './users-routing.module';
import { AddUserFormComponent } from './components/add-user-form/add-user-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { UserTableComponent } from './components/user-table/user-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    UsersRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddUserFormComponent,
    LoginFormComponent,
    UserTableComponent
  ],
  providers: [
    
  ]
})
export class UsersModule { }
