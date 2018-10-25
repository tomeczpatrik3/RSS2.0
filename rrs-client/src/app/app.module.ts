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
import { UserApiService } from "./shared/services/api/user.api.service";
import { BuildingApiService } from "./shared/services/api/building.api.service";
import { ClassroomApiService } from "./shared/services/api/classroom.api.service";
import { SemesterApiService } from "./shared/services/api/semester.api.service";
import { SubjectApiService } from "./shared/services/api/subject.api.service";
import { EventApiService } from "./shared/services/api/event.api.service";
import { ClassReservationApiService } from "./shared/services/api/class-reservation.api.service";
import { EventReservationApiService } from "./shared/services/api/event-reservation.api.service";
import { UniqueBuildingNameValidatorDirective } from "./shared/directives/unique-building-name.directive";
import { TakenBuildingNameValidatorDirective } from "./shared/directives/taken-building-name.directive";
import { UniqueEventNameValidatorDirective } from "./shared/directives/unique-event-name.directive";
import { TakenEventNameValidatorDirective } from "./shared/directives/taken-event-name.directive";
import { UniqueSemesterNameValidatorDirective } from "./shared/directives/unique-semester-name.directive";
import { TakenSemesterNameValidatorDirective } from "./shared/directives/taken-semester-name.directive";
import { UniqueSubjectCodeValidatorDirective } from "./shared/directives/unique-subject-code.directive";
import { TakenSubjectCodeValidatorDirective } from "./shared/directives/taken-subject-code.directive";
import { UniqueUsernameValidatorDirective } from "./shared/directives/unique-username.directive";
import { TakenUsernameValidatorDirective } from "./shared/directives/taken-username.directive";
import { FooterComponent } from './shared/components/footer/footer.component';

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
    FormDialogComponent,

    UniqueBuildingNameValidatorDirective,
    TakenBuildingNameValidatorDirective,
    UniqueEventNameValidatorDirective,
    TakenEventNameValidatorDirective,
    UniqueSemesterNameValidatorDirective,
    TakenSemesterNameValidatorDirective,
    UniqueSubjectCodeValidatorDirective,
    TakenSubjectCodeValidatorDirective,
    UniqueUsernameValidatorDirective,
    TakenUsernameValidatorDirective,
    FooterComponent
  ],
  imports: [
    //Diallógusokhoz:
    ReservationsModule,

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

    //Authentication:
    AuthService,

    //Guards:
    AuthGuard,
    RoleGuard,
    CanDeactivateGuard,

    //Navigation:
    NavigationService,

    //Api:
    BuildingApiService,
    ClassroomApiService,
    SemesterApiService,
    SubjectApiService,
    UserApiService,
    EventApiService,
    ClassReservationApiService,
    EventReservationApiService
  ],
  entryComponents: [
    InfoDialogComponent,
    QuestionDialogComponent,
    FormDialogComponent
  ],
  bootstrap: [AppComponent],
  exports: []
})
export class AppModule {}
