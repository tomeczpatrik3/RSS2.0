import { Component, OnInit } from '@angular/core';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';

@Component({
  selector: 'app-pending-reservations-table',
  templateUrl: './pending-reservations-table.component.html',
  styleUrls: ['./pending-reservations-table.component.css']
})
export class PendingReservationsTableComponent implements OnInit {

  pendingReservations: Reservation[];
  hasPendingReservation: boolean = false;

  constructor(
    private reservationService: ReservationService,
  ) {
  }

  ngOnInit() {
    this.reservationService.findByStatus("PENDING").subscribe(
      res => {
        this.pendingReservations = res;
        this.pendingReservations.length == 0 ? this.hasPendingReservation=false : this.hasPendingReservation=true;
      } 
    )
  }

}