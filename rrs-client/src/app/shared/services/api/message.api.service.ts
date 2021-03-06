import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Message } from "../../models/Message";
import { ApiEndpoints } from "../../config/api-endpoints.config";

/**
 * Az üzenetekhez tartozó service osztály
 */
@Injectable()
export class MessageApiService {
  constructor(private http: HttpClient) {}

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet lekérdezését megvalósító függvény
   * @param id Az azonosító
   */
  findOwnById(id: number): Observable<Message[]> {
    return <Observable<Message[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_FIND_OWN_BY_ID) + `?id=${id}`
      )
    );
  }

  /**
   * Egy adott feladóhoz tartozó üzenetek lekérdezését megvalósító függvény
   * @param sender A feladó felhasználóneve
   */
  findBySender(sender: string): Observable<Message[]> {
    return <Observable<Message[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_FIND_BY_SENDER) +
          `?sender=${sender}`
      )
    );
  }

  /**
   * Egy adott címzetthez tartozó üzenetek lekérdezését megvalósító függvény
   * @param recipient A címzett felhasználóneve
   */
  findByRecipient(recipient: string): Observable<Message[]> {
    return <Observable<Message[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_FIND_BY_RECIPIENT) +
          `?recipient=${recipient}`
      )
    );
  }

  /**
   * A bejelentkezett felhasználóhoz tartozó üzenetek lekérdezését megvalósító függvény
   */
  findOwnMessages(): Observable<Message[]> {
    return <Observable<Message[]>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGES_FIND_OWN_MESSAGES)
      )
    );
  }

  /**
   * Egy adott üzenet elküldését megvalósító függvény
   * @param message Az üzenet
   */
  sendMessage(message: Message): Observable<Message> {
    return <Observable<Message>>(
      this.http.post(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_SEND_MESSAGE),
        message
      )
    );
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
    return <Observable<Message>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_GENERATE_SYSTEM_MESSAGE) +
          `?recipient=${recipient}` +
          `&reservationId=${reservationId}` +
          `&messageType=${messageType}`
      )
    );
  }

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet státuszát "olvasott"-ra állító függvény
   * @param id Az azonosító
   */
  markOwnAsRead(id: number): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_MARK_OWN_AS_READ) + `?id=${id}`
      )
    );
  }

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet státuszát "nem olvasott"-ra állító függvény
   * @param id Az azonosító
   */
  markOwnAsUnread(id: number): Observable<any> {
    return <Observable<any>>(
      this.http.get(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_MARK_OWN_AS_UNREAD) +
          `?id=${id}`
      )
    );
  }

  /**
   * Egy adott azonosítóhoz tartozó (saját) üzenet törlését megvalósító függvény
   * @param id Az azonosító
   */
  deleteOwnById(id: number): Observable<any> {
    return <Observable<any>>(
      this.http.delete(
        ApiEndpoints.getUrl(ApiEndpoints.MESSAGE_DELETE_OWN_BY_ID) + `?id=${id}`
      )
    );
  }
}
