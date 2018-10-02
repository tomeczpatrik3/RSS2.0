import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../../services/reservation.service';
import { AuthService } from '../../../authentication/auth.service';
import { ClassReservation } from '../../../models/ClassReservation';
import { EventReservation } from '../../../models/EventReservation';
import { ClassReservationService } from '../../../services/classReservation.service';

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