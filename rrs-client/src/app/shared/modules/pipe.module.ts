import { NgModule } from "@angular/core";
import { EmptyPipe } from "../pipes/empty.pipe";
import { StatusConverterPipe } from "../pipes/statusConverter.pipe";
import { BooleanConverterPipe } from "../pipes/booleanConverter.pipe";

@NgModule({
  imports: [],
  declarations: [EmptyPipe, StatusConverterPipe, BooleanConverterPipe],
  exports: [EmptyPipe, StatusConverterPipe, BooleanConverterPipe]
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
