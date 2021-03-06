import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClassroomsRoutingModule } from './classrooms-routing.module';
import { AddClassroomFormComponent } from './components/add-classroom-form/add-classroom-form.component';
import { ClassroomTableComponent } from './components/classroom-table/classroom-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClassroomsDataService } from './classrooms.data.service';
import { ClassroomTablePageComponent } from './pages/classroom-table-page/classroom-table-page.component';
import { AddClassroomPageComponent } from './pages/add-classroom-page/add-classroom-page.component';
import { PipeModule } from '../../shared/modules/pipe.module';
import { EditClassroomFormComponent } from './components/edit-classroom-form/edit-classroom-form.component';

@NgModule({
  imports: [
    CommonModule,
    ClassroomsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    PipeModule.forRoot(),
  ],
  declarations: [
    AddClassroomFormComponent,
    ClassroomTableComponent,
    ClassroomTablePageComponent,
    AddClassroomPageComponent,
    EditClassroomFormComponent
  ],
  providers: [
    ClassroomsDataService
  ],
  exports: [
    EditClassroomFormComponent
  ]
})
export class ClassroomsModule { }
