import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../shared/services/auth.service';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {
  username: string;

  constructor(
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.username = this.authService.getUsername();
  }
}