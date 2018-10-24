import { NgModule }      from '@angular/core';
import { EmptyPipe }          from '../pipes/empty.pipe';
import { StatusConverterPipe }          from '../pipes/statusConverter.pipe';

@NgModule({
    imports:        [],
    declarations:   [EmptyPipe, StatusConverterPipe],
    exports:        [EmptyPipe, StatusConverterPipe],
})

export class PipeModule {
  static forRoot() {
     return {
         ngModule: PipeModule,
         providers: [],
     };
  }
} 