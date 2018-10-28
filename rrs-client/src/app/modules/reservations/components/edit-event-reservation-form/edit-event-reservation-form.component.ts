import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { EventReservation } from "../../../../shared/models/EventReservation";
import { EventReservationsDataService } from "../../event-reservations.data.service";

@Component({
  selector: "app-edit-event-reservation-form",
  templateUrl: "./edit-event-reservation-form.component.html",
  styleUrls: ["./edit-event-reservation-form.component.css"]
})
export class EditEventReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: EventReservation;

  constructor(private eventReservationService: EventReservationsDataService) {}

  /**
   * Az inicializálásért felelős függvény
   * A megfelelő foglalás betöltése, valamint a
   * kezdés és befejezés átkonvertálása
   */
  ngOnInit() {
    this.eventReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
      this.model.startDate = this.model.startDate.substring(0, 16);
      this.model.endDate = this.model.endDate.substring(0, 16);
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.eventReservationService
      .update(this.reservationID, this.model)
      .subscribe(result => console.log(result));
    this.submitEvent.next(true);
  }

  /**
   * A kilépésért felelős függvény
   */
  onExit() {
    this.submitEvent.next(false);
  }
}
