import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SemestersRoutingModule } from './reservations-routing.module';
import { AddSemesterFormComponent } from './components/add-semester-form/add-semester-form.component';
import { SemesterTableComponent } from './components/semester-table/semester-table.component';

@NgModule({
  imports: [
    CommonModule,
    SemestersRoutingModule
  ],
  declarations: [
    AddSemesterFormComponent,
    SemesterTableComponent
  ]
})
export class SemestersModule { }
