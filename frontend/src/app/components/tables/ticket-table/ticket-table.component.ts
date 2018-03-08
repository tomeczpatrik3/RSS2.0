import { Component, OnInit } from '@angular/core';
import { Ticket } from '../../../models/Ticket';
import { TicketService } from '../../../services/ticket.service';

@Component({
  selector: 'app-ticket-table',
  templateUrl: './ticket-table.component.html',
  styleUrls: ['./ticket-table.component.css']
})
export class TicketTableComponent implements OnInit {

  tickets: Ticket[];

  constructor(
    private ticketService: TicketService,
  ) {
  }

  ngOnInit() {
    this.ticketService.getAll().subscribe(
      res => this.tickets = res
    )
  }

}