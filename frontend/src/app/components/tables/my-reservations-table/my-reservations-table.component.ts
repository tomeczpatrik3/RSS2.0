import { Component, OnInit } from '@angular/core';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';
import { AuthService } from '../../../authentication/auth.service';

@Component({
  selector: 'app-my-reservations-table',
  templateUrl: './my-reservations-table.component.html',
  styleUrls: ['./my-reservations-table.component.css']
})
export class MyReservationsTableComponent implements OnInit {

  reservations: Reservation[];
  username: string;

  constructor(
    private reservationService: ReservationService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.username = this.authService.getUsername();

    this.reservationService.findByUsername(this.username).subscribe(
      res => this.reservations = res
    )
  }
}