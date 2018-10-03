import { Component, OnInit, Input } from "@angular/core";
import { ClassReservationService } from "../../../services/class-reservation.service";
import { ClassReservation } from "../../../models/ClassReservation";

@Component({
  selector: "app-class-reservation-table",
  templateUrl: "./class-reservation-table.component.html",
  styleUrls: ["./class-reservation-table.component.css"]
})
export class ClassReservationTableComponent implements OnInit {
  @Input()
  user: string;
  @Input()
  pending: boolean;
  reservations: ClassReservation[];

  constructor(private classReservationService: ClassReservationService) {}

  ngOnInit() {
    if (!this.user && !this.pending) {
      //Null, empty
      this.classReservationService
        .getAccepted()
        .subscribe(res => (this.reservations = res));
    } else {
      this.classReservationService
        .findByUsername(this.user)
        .subscribe(res => (this.reservations = res));
    }
  }
}
