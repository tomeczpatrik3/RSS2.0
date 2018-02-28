import { Injectable, Input } from '@angular/core';
import { MatSnackBar} from "@angular/material";

@Injectable()
export class SnackbarService {

  constructor(
    private snackBar: MatSnackBar
  ) { }

  open(message: string, action: string, duration?: number) {
    this.snackBar.open(message, action, {
      duration: duration == null ? 3000 : duration * 1000
    })
  }
}
