import { Component, OnInit } from "@angular/core";
import { Event } from "../../../../shared/models/Event";
import { EventApiService } from "../../../../shared/services/api/event.api.service";
import { DialogService } from "../../../../shared/services/dialog.service";
import { ReservationType } from "../../../../shared/enums/ReservationType";
import { FormType } from "../../../../shared/enums/FormType";
import { FormDialogComponent } from "../../../../shared/components/dialogs/form-dialog/form-dialog.component";

@Component({
  selector: "app-events-table",
  templateUrl: "./events-table.component.html",
  styleUrls: ["./events-table.component.css"]
})
export class EventsTableComponent implements OnInit {
  /*Az események*/
  reservationEvents: Event[];

  constructor(
    private eventService: EventApiService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * Események betöltése
   */
  ngOnInit() {
    this.eventService
      .getEvents()
      .subscribe(events => (this.reservationEvents = events));
  }

  /**
   * A megfelelő (eseményhez tartozó) dialógus megjelenítéséért felelős függvény
   * @param event Az esemény
   */
  openDetails(event: Event) {
    let formType: string;
    if (event.info.type == ReservationType.EVENT) {
      formType = FormType.OBSERVE_EVENT_RESERVATION_FORM;
    } else {
      formType = FormType.OBSERVE_CLASS_RESERVATION_FORM;
    }

    this.dialogService.openFormDialog(
      "Foglalás megtekintése:",
      formType,
      event.info.id,
      FormDialogComponent
    );
  }
}
