import { BaseEntity } from "./BaseEntity";

export class Message extends BaseEntity {
  sender: string;
  recipient: string;
  message: string;
  unread: boolean;

  public constructor(
    sender?: string,
    recipient?: string,
    message?: string,
    unread?: boolean,
    id?: number
  ) {
    super(id);
    this.sender = sender || "";
    this.recipient = recipient || "";
    this.message = message || "";
    this.unread = unread || true;
  }
}
