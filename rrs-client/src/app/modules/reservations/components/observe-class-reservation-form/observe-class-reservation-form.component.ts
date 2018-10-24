import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ClassReservation } from '../../../../shared/models/ClassReservation';
import { ClassReservationsDataService } from "../../class-reservations.data.service";
import { UserApiService } from '../../../../shared/services/api/user.api.service';
import { SubjectApiService } from '../../../../shared/services/api/subject.api.service';


@Component({
  selector: 'app-observe-class-reservation-form',
  templateUrl: './observe-class-reservation-form.component.html',
  styleUrls: ['./observe-class-reservation-form.component.css']
})
export class ObserveClassReservationFormComponent implements OnInit {

  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  model: ClassReservation;
  fullName: string;
  subjectName: string;

  constructor(
    private classReservationService: ClassReservationsDataService,
    private userService: UserApiService,
    private subjectService: SubjectApiService
  ) {}

  ngOnInit() {
    this.classReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
      this.userService.getNameByUsername(this.model.username).subscribe(res => {
        this.fullName = res.name;
      });
      this.subjectService.getSubjectName(this.model.subjectCode).subscribe(res =>{
        this.subjectName = res.name;
      });
    });
  }

  onExit() {
    this.submitEvent.next(false);
  }
}
