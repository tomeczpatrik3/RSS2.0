import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service';
import { Ticket } from '../../models/Ticket';
import { Statuses } from '../../config/statuses.config';
import { DialogService } from '../../services/dialog.service';
import { InfoDialogComponent } from '../dialogs/info-dialog/info-dialog.component';

@Component({
  selector: 'app-pending-tickets',
  templateUrl: './pending-tickets.component.html',
  styleUrls: ['./pending-tickets.component.css']
})
export class PendingTicketsComponent implements OnInit {

  pendingTickets: Ticket[];
  hasPendingTicket: boolean = false;

  constructor(
    private ticketService: TicketService,
    private dialogService: DialogService
  ) { }

  ngOnInit() {
    this.updateTickets();
  }

  updateTickets() {
    this.ticketService.findByStatus('PENDING').subscribe(
      res => {
        this.pendingTickets = res;
        this.pendingTickets.length == 0 ? this.hasPendingTicket = false : this.hasPendingTicket = true;
      }
    )
  }

  accept(ticket) {
    this.ticketService.setStatus(ticket.id, Statuses.ACCEPTED).subscribe(
      res => {
        this.dialogService.openDialog("Értesítés:", "A várakozó foglalás sikeresen elfogadva!", InfoDialogComponent);
        this.updateTickets();
      },
      err => {
        console.log(err);
      }
    )
  }

  decline(ticket) {
    this.ticketService.setStatus(ticket.id, Statuses.DECLINED).subscribe(
      res => {
        this.dialogService.openDialog("Értesítés:", "A várakozó foglalás sikeresen elutasítva!", InfoDialogComponent);
        this.updateTickets();
      },
      err => {
        console.log(err);
      }
    )
  }

}
