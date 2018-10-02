import { Component, OnInit, Input } from '@angular/core';

import { ClassReservationService } from '../../../services/classReservation.service';

import { EventReservation } from '../../../models/EventReservation';
import { User } from '../../../models/User';
import { Subject } from '../../../models/Subject';
import { Classroom } from '../../../models/Classroom';
import { UserService } from '../../../services/user.service';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { EventReservationService } from '../../../services/eventReservation.service';

@Component({
  selector: 'app-event-reservation-table',
  templateUrl: './event-reservation-table.component.html',
  styleUrls: ['./event-reservation-table.component.css']
})
export class EventReservationTableComponent implements OnInit {
  @Input() selectedUser: string;
  reservations: EventReservation[];

  constructor(
    private eventReservationService: EventReservationService,
  ) {}

  ngOnInit() {
    if (!this.selectedUser) { //Null, empty
      this.eventReservationService.getAccepted().subscribe(
        res => this.reservations = res
      )
    }
    else {
      this.eventReservationService.findByUsername(this.selectedUser).subscribe(
        res => this.reservations = res
      )
    }
  }
}