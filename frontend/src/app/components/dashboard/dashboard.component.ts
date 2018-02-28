import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from "@angular/material";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
  }

  open() {
    this.snackBar.open("aSD", "OK");
  }
}
