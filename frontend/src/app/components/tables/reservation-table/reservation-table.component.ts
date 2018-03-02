import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../../services/reservation.service';
import { Reservation } from '../../../models/Reservation';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-reservation-table',
  templateUrl: './reservation-table.component.html',
  styleUrls: ['./reservation-table.component.css']
})
export class ReservationTableComponent implements OnInit {

  reservations: Reservation[];

  constructor(
    private reservationService: ReservationService
  ) {
  }

  ngOnInit() {
    this.reservationService.getAll().subscribe(
      res => this.reservations = res
    )
  }
}