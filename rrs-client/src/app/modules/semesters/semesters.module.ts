import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SemestersRoutingModule } from './reservations-routing.module';
import { AddSemesterFormComponent } from './components/add-semester-form/add-semester-form.component';
import { SemesterTableComponent } from './components/semester-table/semester-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SemesterDataService } from './semesters.data.service';
import { AddSemesterPageComponent } from './pages/add-semester-page/add-semester-page.component';
import { SemesterPageComponent } from './pages/semester-page/semester-page.component';

@NgModule({
  imports: [
    CommonModule,
    SemestersRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddSemesterFormComponent,
    SemesterTableComponent,
    AddSemesterPageComponent,
    SemesterPageComponent
  ],
  providers: [
    SemesterDataService
  ]
})
export class SemestersModule { }
