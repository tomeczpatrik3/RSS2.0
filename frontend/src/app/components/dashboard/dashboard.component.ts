import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from "@angular/material";
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(
  ) { }

  ngOnInit() {
  }
}
