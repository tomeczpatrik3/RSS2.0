import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { RoutingModule } from './routing/routing.module';

import { AppComponent } from './components/app/app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { ValidatorService } from './services/validator.service';
import { MaterialModule } from './modules/material.module';
import { TablesModule } from './modules/tables.module';
import { AddFormsModule } from './modules/forms.module';
import { AuthService } from './authentication/auth.service';
import { DialogService } from './services/dialog.service';

import { AuthGuardService } from './guards/auth-guard.service';
import { RoleGuardService } from './guards/role-guard.service';
import { FormGuardService } from './guards/form-guard.service';
import { NavigationService } from './services/navigation.service';

import { JwtModule, JwtHelperService } from '@auth0/angular-jwt';

import { QuestionDialogComponent } from './components/dialogs/question-dialog/question-dialog.component';
import { InfoDialogComponent } from './components/dialogs/info-dialog/info-dialog.component';
import { LogoutComponent } from './components/logout/logout.component';

import { UpperCasePipe } from '@angular/common';
import { EditReservationDialogComponent } from './components/dialogs/edit-reservation-dialog/edit-reservation-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    InfoDialogComponent,
    QuestionDialogComponent,
    LogoutComponent,
    EditReservationDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    //Own modules:
    RoutingModule,
    MaterialModule,
    TablesModule,
    AddFormsModule,
    
    //Minden get esetén csatolja a tokent:
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('token');
        },
        whitelistedDomains: ['localhost:8080']
      }
    }),

    //Dropdown-hoz:
    NgbModule.forRoot()
  ],
  providers: [
    ValidatorService,
    DialogService,
    JwtHelperService,
    AuthService,
    AuthGuardService,
    RoleGuardService,
    FormGuardService,
    NavigationService,
  ],
  entryComponents: [
    InfoDialogComponent,
    QuestionDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
