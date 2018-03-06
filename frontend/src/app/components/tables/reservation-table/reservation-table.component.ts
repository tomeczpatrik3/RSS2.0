import { Component, OnInit } from '@angular/core';

import { ReservationService } from '../../../services/reservation.service';

import { Reservation } from '../../../models/Reservation';
import { User } from '../../../models/User';
import { Subject } from '../../../models/Subject';
import { Classroom } from '../../../models/Classroom';
import { UserService } from '../../../services/user.service';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';

@Component({
  selector: 'app-reservation-table',
  templateUrl: './reservation-table.component.html',
  styleUrls: ['./reservation-table.component.css']
})
export class ReservationTableComponent implements OnInit {

  reservations: Reservation[];

  constructor(
    private reservationService: ReservationService,
  ) {}

  ngOnInit() {
    this.reservationService.getAll().subscribe(
      res => this.reservations = res
    )
}