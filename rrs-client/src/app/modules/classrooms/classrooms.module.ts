import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClassroomsRoutingModule } from './classrooms-routing.module';
import { AddClassroomFormComponent } from './components/add-classroom-form/add-classroom-form.component';
import { ClassroomTableComponent } from './components/classroom-table/classroom-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClassroomsDataService } from './classrooms.data.service';
import { ClassroomTablePageComponent } from './pages/classroom-table-page/classroom-table-page.component';
import { AddClassroomPageComponent } from './pages/add-classroom-page/add-classroom-page.component';

@NgModule({
  imports: [
    CommonModule,
    ClassroomsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddClassroomFormComponent,
    ClassroomTableComponent,
    ClassroomTablePageComponent,
    AddClassroomPageComponent
  ],
  providers: [
    ClassroomsDataService
  ]
})
export class ClassroomsModule { }
