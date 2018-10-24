import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SemestersRoutingModule } from './reservations-routing.module';
import { AddSemesterFormComponent } from './components/add-semester-form/add-semester-form.component';
import { SemesterTableComponent } from './components/semester-table/semester-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    SemestersRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddSemesterFormComponent,
    SemesterTableComponent
  ]
})
export class SemestersModule { }
