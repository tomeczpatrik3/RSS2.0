import { Component, OnInit } from "@angular/core";
import { MessagesDataService } from "../../messages.data.service";
import { Message } from "../../../../shared/models/Message";
import { DialogService } from "../../../../shared/services/dialog.service";
import { TextUtils } from "../../../../shared/utils/text-utils";
import { ErrorDialogComponent } from "../../../../shared/components/dialogs/error-dialog/error-dialog.component";

@Component({
  selector: "app-message-table",
  templateUrl: "./message-table.component.html",
  styleUrls: ["./message-table.component.css"]
})
export class MessageTableComponent implements OnInit {
  /*Az üzenetek*/
  messages: Message[];

  constructor(
    private messageService: MessagesDataService,
    private dialogService: DialogService
  ) {}

  /**
   * Az inicializálásért felelős függvény
   * Az üzenetek betöltése
   */
  ngOnInit() {
    this.refreshTable();
  }

  /**
   * A táblázat frissítéséért felelős függvény
   */
  refreshTable(): void {
    this.messageService
      .findOwnMessages()
      .subscribe(messages => (this.messages = messages));
  }

  /**
   * Egy adott üzenet státuszának "olvasott"-ra állításáért felelős függvény
   * @param message Az üzenet
   */
  markAsRead(message: Message): void {
    this.messageService
      .markOwnAsRead(message.id)
      .subscribe(
        () => this.refreshTable(),
        error =>
          this.dialogService.openDialog(
            "Üzenet státuszának módosítása:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
          )
      );
  }

  /**
   * Egy adott üzenet törléséért felelős függvény
   * @param message Az üzenet
   */
  delete(message: Message): void {
    this.messageService
      .deleteOwnById(message.id)
      .subscribe(
        () => this.refreshTable(),
        error =>
          this.dialogService.openDialog(
            "Üzenet törlése:",
            TextUtils.addBreaks(error.error),
            ErrorDialogComponent
          )
      );
  }
}
