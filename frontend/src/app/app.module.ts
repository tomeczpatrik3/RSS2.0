import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { RoutingModule } from './routing/routing.module';

import { AppComponent } from './components/app/app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginFormComponent } from './components/forms/login-form/login-form.component';
import { AddUserFormComponent } from './components/forms/add-user-form/add-user-form.component';
import { AddClassroomFormComponent } from './components/forms/add-classroom-form/add-classroom-form.component';
import { AddReservationFormComponent } from './components/forms/add-reservation-form/add-reservation-form.component';
import { SubjectTableComponent } from './components/tables/subject-table/subject-table.component';
import { AddSubjectFormComponent } from './components/forms/add-subject-form/add-subject-form.component';
import { FilterReservationFormComponent } from './components/forms/filter-reservation-form/filter-reservation-form.component';
import { ValidatorService } from './services/validator.service';
import { HttpService } from './services/http.service';
import { MaterialModule } from './modules/material.module';
import { InfoDialogComponent } from './components/dialogs/info-dialog/info-dialog.component';
import { TablesModule } from './modules/tables.module';
import { AddFormsModule } from './modules/add-forms.module';
import { AuthService } from './services/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    LoginFormComponent,
    FilterReservationFormComponent,
    InfoDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    //Own modules:
    RoutingModule,
    MaterialModule,
    TablesModule,
    AddFormsModule
  ],
  providers: [
    ValidatorService,
    HttpService,
    AuthService
  ],
  entryComponents: [
    InfoDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
