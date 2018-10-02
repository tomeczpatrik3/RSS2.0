import { Component, OnInit, Input } from '@angular/core';

import { ClassReservationService } from '../../../services/classReservation.service';

import { ClassReservation } from '../../../models/ClassReservation';
import { User } from '../../../models/User';
import { Subject } from '../../../models/Subject';
import { Classroom } from '../../../models/Classroom';
import { UserService } from '../../../services/user.service';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';

@Component({
  selector: 'app-class-reservation-table',
  templateUrl: './class-reservation-table.component.html',
  styleUrls: ['./class-reservation-table.component.css']
})
export class ClassReservationTableComponent implements OnInit {

  @Input() user: string;
  @Input() pending: boolean;
  reservations: ClassReservation[];

  constructor(
    private classReservationService: ClassReservationService
  ) {
  }

  ngOnInit() {
    if (!this.user && !this.pending) { //Null, empty
      this.classReservationService.getAccepted().subscribe(
        res => this.reservations = res
      )
    }
    else {
      this.classReservationService.findByUsername(this.user).subscribe(
        res => this.reservations = res
      )
    }
  }
}