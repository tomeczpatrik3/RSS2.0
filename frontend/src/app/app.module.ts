import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { HTTP_INTERCEPTORS } from "@angular/common/http";

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { RoutingModule } from "./routing/routing.module";
import { TablesModule } from "./modules/tables.module";
import { OwnFormsModule } from "./modules/forms.module";
import { MaterialModule } from "./modules/material.module";
import { OwnCalendarModule } from "./modules/calendar.module";

import { AppComponent } from "./components/app/app.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { NavbarComponent } from "./components/navbar/navbar.component";

import { ValidatorService } from "./services/validator.service";
import { AuthService } from "./authentication/auth.service";
import { DialogService } from "./services/dialog.service";

import { AuthGuard } from "./guards/auth-guard.service";
import { RoleGuard } from "./guards/role-guard.service";
import { CanDeactivateGuard } from "./guards/can-deactivate-guard.service";

import { NavigationService } from "./services/navigation.service";

import { JwtModule, JwtHelperService } from "@auth0/angular-jwt";

import { QuestionDialogComponent } from "./components/dialogs/question-dialog/question-dialog.component";
import { InfoDialogComponent } from "./components/dialogs/info-dialog/info-dialog.component";
import { EditDialogComponent } from "./components/dialogs/edit-dialog/edit-dialog.component";

import { UpperCasePipe } from "@angular/common";

import { httpInterceptorProviders } from "./interceptors/providers";

import { LogoutComponent } from "./components/logout/logout.component";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    InfoDialogComponent,
    QuestionDialogComponent,
    EditDialogComponent,
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,

    //Own modules:
    RoutingModule,
    MaterialModule,
    TablesModule,
    OwnFormsModule,
    OwnCalendarModule,

    //Minden get esetén csatolja a tokent:
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem("token");
        },
        whitelistedDomains: ["localhost:8080"]
      }
    }),

    //Dropdown-hoz:
    NgbModule.forRoot()
  ],
  providers: [
    ValidatorService,
    DialogService,
    JwtHelperService,
    httpInterceptorProviders,
    AuthService,
    AuthGuard,
    RoleGuard,
    CanDeactivateGuard,
    NavigationService,
  ],
  entryComponents: [
    InfoDialogComponent,
    QuestionDialogComponent,
    EditDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
