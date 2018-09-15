import { Component, OnInit } from '@angular/core';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';
import { Statuses } from '../../../config/statuses.config';

@Component({
  selector: 'app-pending-reservations-table',
  templateUrl: './pending-reservations-table.component.html',
  styleUrls: ['./pending-reservations-table.component.css']
})
export class PendingReservationsTableComponent implements OnInit {

  pendingReservations: Reservation[];

  constructor(
    private reservationService: ReservationService,
  ) {
  }

  ngOnInit() {
    this.reservationService.findByStatus(Statuses.PENDING).subscribe(
      res => this.pendingReservations = res
    )
  }

}