import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { ClassReservation } from "../../../../shared/models/ClassReservation";
import { ClassReservationsDataService } from "../../class-reservations.data.service";

@Component({
  selector: "app-edit-class-reservation-form",
  templateUrl: "./edit-class-reservation-form.component.html",
  styleUrls: ["./edit-class-reservation-form.component.css"]
})
export class EditClassReservationFormComponent implements OnInit {
  /*A foglalás azonosítója*/
  @Input()
  reservationID: number;

  @Output()
  submitEvent = new EventEmitter<boolean>();

  /*A foglalás*/
  model: ClassReservation;

  constructor(private classReservationService: ClassReservationsDataService) {}

  /**
   * Az inicializálásért felelős függvény
   * A megfelelő foglalás betöltése
   */
  ngOnInit() {
    this.classReservationService.findById(this.reservationID).subscribe(res => {
      this.model = res;
    });
  }

  /**
   * A módosításért felelős függvény
   */
  onSubmit() {
    this.classReservationService
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
