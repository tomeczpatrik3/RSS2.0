import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from "./app-routing.module";

import { AppComponent } from "./shared/components/app/app.component";
import { DashboardComponent } from "./shared/components/dashboard/dashboard.component";
import { NavbarComponent } from "./shared/components/navbar/navbar.component";

import { ValidatorService } from "./shared/services/validator.service";
import { AuthService } from "./shared/services/auth.service";
import { DialogService } from "./shared/services/dialog.service";

import { AuthGuard } from "./shared/guards/auth-guard.service";
import { RoleGuard } from "./shared/guards/role-guard.service";
import { CanDeactivateGuard } from "./shared/guards/can-deactivate-guard.service";

import { NavigationService } from "./shared/services/navigation.service";

import { JwtModule, JwtHelperService } from "@auth0/angular-jwt";

import { QuestionDialogComponent } from "./shared/components/dialogs/question-dialog/question-dialog.component";
import { InfoDialogComponent } from "./shared/components/dialogs/info-dialog/info-dialog.component";
import { FormDialogComponent } from "./shared/components/dialogs/form-dialog/form-dialog.component";

import { httpInterceptorProviders } from "./shared/interceptors/providers";

import { MaterialModule } from "./shared/modules/material.module";
import { ReservationsModule } from "./modules/reservations/reservations.module";

export function jwtTokenGetter() {
  return localStorage.getItem("token");
}

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavbarComponent,
    InfoDialogComponent,
    QuestionDialogComponent,
    FormDialogComponent
  ],
  imports: [
    //Diallógusokhoz:

    BrowserModule,
    HttpClientModule,
    MaterialModule,

    /**
     * Minden get esetén csatolja a tokent:
     */
    JwtModule.forRoot({
      config: {
        tokenGetter: jwtTokenGetter,
        whitelistedDomains: ["localhost:8080"]
      }
    }),

    /**
     * A dropdown számára:
     */
    NgbModule.forRoot(),

    /**
     * Routing:
     */
    AppRoutingModule
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
    NavigationService
  ],
  entryComponents: [
    InfoDialogComponent,
    QuestionDialogComponent,
    FormDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
