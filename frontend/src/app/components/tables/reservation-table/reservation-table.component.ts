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

  reservationData = new ReservationDataSource(this.reservationService);
  columnsToDisplay = ['teacher', 'subject', 'classroom', 'startDate', 'endDate', 'day', 'startTime', 'endTime'];

  constructor(
    private reservationService: ReservationService
  ) {
  }

  ngOnInit() {
  }

}

export class ReservationDataSource extends DataSource<any> {

  constructor (
    private reservationService: ReservationService
  ) {
    super();
  }

  connect(): Observable<Reservation[]> {
    return this.reservationService.getAll();
  }

  disconnect() {}
}
