import { NgModule } from "@angular/core";
import { EmptyPipe } from "../pipes/empty.pipe";
import { StatusConverterPipe } from "../pipes/status-converter.pipe";
import { BooleanConverterPipe } from "../pipes/boolean-converter.pipe";
import { SemesterStatusConverterPipe } from "../pipes/semester-status-converter.pipe";

@NgModule({
  imports: [],
  declarations: [
    EmptyPipe,
    StatusConverterPipe,
    BooleanConverterPipe,
    SemesterStatusConverterPipe
  ],
  exports: [
    EmptyPipe,
    StatusConverterPipe,
    BooleanConverterPipe,
    SemesterStatusConverterPipe
  ]
})

/**
 * A pipe-okat tartalmazó modul
 */
export class PipeModule {
  static forRoot() {
    return {
      ngModule: PipeModule,
      providers: []
    };
  }
}
