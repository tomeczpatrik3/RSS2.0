import { Component, OnInit } from '@angular/core';
import { ClassroomService } from '../../../services/classroom.service';
import { SubjectService } from '../../../services/subject.service';
import { Validators, FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';
import { MatDialog } from '@angular/material';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';
import { ValidatorService } from '../../../services/validator.service';
import { DialogService } from '../../../services/dialog.service';
import { AuthService } from '../../../authentication/auth.service';
import { BuildingService } from '../../../services/building.service';
import { Router } from '@angular/router';
import { Subject } from '../../../models/Subject';
import { Building } from '../../../models/Building';
import { ReservationForm } from '../../../models/ReservationForm';
import { SemesterService } from '../../../services/semester.service';
import { Semester } from '../../../models/Semester';

@Component({
  selector: 'app-add-reservation-form',
  templateUrl: './add-reservation-form.component.html',
  styleUrls: ['./add-reservation-form.component.css']
})
export class AddReservationFormComponent implements OnInit {

  types: string[] = [
    "Esemény foglalása", 
    "Tantárgyra vonatkozó egyszeri foglalás", 
    "Tantárgyra és szemeszterre vonatkozó foglalás"
  ];

  selectedType: string;

  constructor()
  {}

  ngOnInit() {}

  private selectType(type: string) {
    this.selectedType = type;
  }
}
