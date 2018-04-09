import { Component, OnInit } from '@angular/core';
import { Statuses } from '../../../config/statuses.config';
import { DialogService } from '../../../services/dialog.service';
import { InfoDialogComponent } from '../../dialogs/info-dialog/info-dialog.component';
import { Reservation } from '../../../models/Reservation';
import { ReservationService } from '../../../services/reservation.service';
import { Type } from '../../../models/Type';
import { TypeService } from '../../../services/type.service';

@Component({
  selector: 'app-pending-reservations',
  templateUrl: './pending-reservations.component.html',
  styleUrls: ['./pending-reservations.component.css']
})
export class PendingReservationsComponent implements OnInit {

  pendingReservations: Reservation[];
  types: Type[];
  haspendingReservation: boolean = false;

  constructor(
    private reservationService: ReservationService,
    private typeService: TypeService,
    private dialogService: DialogService
  ) { }

  ngOnInit() {
    this.getTypes();
  }

  getTypes() {
    this.typeService.getAll().subscribe(
      res => this.types = res
    )
  }

  selectType(type: string) {
    this.updateReservations(type);
  }

  updateReservations(type: string) {
    this.reservationService.findByStatusAndType('PENDING', type).subscribe(
      res => {
        this.pendingReservations = res;
        this.pendingReservations.length == 0 ? this.haspendingReservation = false : this.haspendingReservation = true;
      }
    )
  }

  accept(reservation) {
    this.reservationService.setStatus(reservation.id, Statuses.ACCEPTED).subscribe(
      res => {
        this.dialogService.openDialog("Értesítés:", "A várakozó foglalás sikeresen elfogadva!", InfoDialogComponent);
        //this.updateReservations();
      },
      err => {
        console.log(err);
      }
    )
  }

  decline(reservation) {
    this.reservationService.setStatus(reservation.id, Statuses.DECLINED).subscribe(
      res => {
        this.dialogService.openDialog("Értesítés:", "A várakozó foglalás sikeresen elutasítva!", InfoDialogComponent);
        //this.updateReservations();
      },
      err => {
        console.log(err);
      }
    )
  }

}
