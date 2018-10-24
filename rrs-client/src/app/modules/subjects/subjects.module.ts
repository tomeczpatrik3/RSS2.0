import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubjectsRoutingModule } from './subjects-routing.module';
import { AddSubjectFormComponent } from './components/add-subject-form/add-subject-form.component';
import { SubjectTableComponent } from './components/subject-table/subject-table.component';

@NgModule({
  imports: [
    CommonModule,
    SubjectsRoutingModule
  ],
  declarations: [
    AddSubjectFormComponent,
    SubjectTableComponent
  ]
})
export class SubjectsModule { }
