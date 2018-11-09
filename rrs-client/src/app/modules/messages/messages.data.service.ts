import { MessageApiService } from "../../shared/services/api/message.api.service";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Message } from "../../shared/models/Message";

@Injectable()
export class MessagesDataService {
  constructor(private api: MessageApiService) {}

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findOwnById(id: number): Observable<Message[]> {
    return this.api.findOwnById(id);
  }

  /**
   * Egy adott feladóhoz tartozó üzenetek lekérdezését megvalósító függvény
   * @param sender A feladó felhasználóneve
   */
  findBySender(sender: string): Observable<Message[]> {
    return this.api.findBySender(sender);
  }

  /**
   * Egy adott címzetthez tartozó üzenetek lekérdezését megvalósító függvény
   * @param recipient A címzett felhasználóneve
   */
  findByRecipient(recipient: string): Observable<Message[]> {
    return this.api.findBySender(recipient);
  }

  /**
   * A bejelentkezett felhasználóhoz tartozó üzenetek lekérdezését megvalósító függvény
   */
  findOwnMessages(): Observable<Message[]> {
    return this.api.findOwnMessages();
  }

  /**
   * Egy adott üzenet elküldését megvalósító függvény
   * @param message Az üzenet
   */
  sendMessage(message: Message): Observable<Message> {
    return this.api.sendMessage(message);
  }

  /**
   * Egy adott rendszerüzenet generálásáért felelős függvény
   * @param recipient A címzett
   * @param reservationId A foglalás azonosítója
   * @param messageType Az üzenet típusa
   */
  generateSystemMessage(
    recipient: string,
    reservationId: number,
    messageType: string
  ): Observable<Message> {
    return this.api.generateSystemMessage(
      recipient,
      reservationId,
      messageType
    );
  }

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet státuszát "olvasott"-ra állító függvény
   * @param id Az azonosító
   */
  markOwnAsRead(id: number): Observable<any> {
    return this.api.markOwnAsRead(id);
  }

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet státuszát "nem olvasott"-ra állító függvény
   * @param id Az azonosító
   */
  markOwnAsUnread(id: number): Observable<any> {
    return this.api.markOwnAsUnread(id);
  }

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet törlését megvalósító függvény
   * @param id Az azonosító
   */
  deleteOwnById(id: number): Observable<any> {
    return this.api.deleteOwnById(id);
  }
}
