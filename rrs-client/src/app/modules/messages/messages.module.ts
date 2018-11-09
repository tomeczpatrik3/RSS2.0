import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MessagesRoutingModule } from './messages-routing.module';
import { MessageTableComponent } from './components/message-table/message-table.component';
import { MessagesDataService } from './messages.data.service';
import { MessageTablePageComponent } from './pages/message-table-page/message-table-page.component';

@NgModule({
  imports: [
    CommonModule,
    MessagesRoutingModule
  ],
  declarations: [MessageTableComponent, MessageTablePageComponent],
  providers: [
    MessagesDataService
  ]
})
export class MessagesModule { }
