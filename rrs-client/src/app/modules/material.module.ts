import { NgModule,CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SnackbarService } from '../services/snackbar.service';

import {
  MatButtonModule,
  MatMenuModule,
  MatToolbarModule,
  MatIconModule,
  MatCardModule,
  MatTableModule,
  MatFormFieldModule,
  MatDialogModule
} from '@angular/material';


@NgModule({
  imports: [
      CommonModule,
      BrowserAnimationsModule,
      MatSnackBarModule,
      MatButtonModule,
      MatMenuModule,
      MatToolbarModule,
      MatIconModule,
      MatCardModule,
      MatTableModule,
      MatFormFieldModule,
      MatDialogModule
    ],
  schemas: [
      CUSTOM_ELEMENTS_SCHEMA, 
      NO_ERRORS_SCHEMA],
  declarations: [
  ],
  providers: [
    SnackbarService
  ],
  exports: [
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatSnackBarModule,
    MatTableModule,
    MatFormFieldModule,
    MatDialogModule
  ]
})
export class MaterialModule {}