import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { UserApiService } from "../../../../shared/services/api/user.api.service";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationsDataService } from "../../event-reservations.data.service";

@Component({
  selector: "app-observe-event-reservation-form",
  templateUrl: "./observe-event-reservation-form.component.html",
  styleUrls: ["./observe-event-reservation-form.component.css"]
})
export class ObserveEventReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: EventReservation;
  /*A teljes név*/
  fullName: string;

  constructor(
    private eventReservationService: EventReservationsDataService,
    private userService: UserApiService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * A foglalás, valamint a teljes név betöltése.
   * Kezdés és befejezés formázása
   */
  ngOnInit() {
    this.eventReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
      this.model.startDate = this.model.startDate.substring(0, 16);
      this.model.endDate = this.model.endDate.substring(0, 16);
      this.userService.getNameByUsername(this.model.username).subscribe(res => {
        this.fullName = res.name;
      });
    });
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }
}
