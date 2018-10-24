import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClassroomsRoutingModule } from './classrooms-routing.module';
import { AddClassroomFormComponent } from './components/add-classroom-form/add-classroom-form.component';
import { ClassroomTableComponent } from './components/classroom-table/classroom-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    ClassroomsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddClassroomFormComponent,
    ClassroomTableComponent
  ]
})
export class ClassroomsModule { }
