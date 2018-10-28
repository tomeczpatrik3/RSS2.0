import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SubjectsRoutingModule } from "./subjects-routing.module";
import { AddSubjectFormComponent } from "./components/add-subject-form/add-subject-form.component";
import { SubjectTableComponent } from "./components/subject-table/subject-table.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SubjectsDataService } from "./subjects.data.service";
import { SubjectApiService } from "../../shared/services/api/subject.api.service";
import { SubjectPageComponent } from "./pages/subject-page/subject-page.component";
import { AddSubjectPageComponent } from "./pages/add-subject-page/add-subject-page.component";
import { EditSubjectFormComponent } from "./components/edit-subject-form/edit-subject-form.component";

@NgModule({
  imports: [
    CommonModule,
    SubjectsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    AddSubjectFormComponent,
    SubjectTableComponent,
    SubjectPageComponent,
    AddSubjectPageComponent,
    EditSubjectFormComponent
  ],
  providers: [SubjectApiService, SubjectsDataService],
  exports: [EditSubjectFormComponent]
})
export class SubjectsModule {}
