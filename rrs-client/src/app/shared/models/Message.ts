import { BaseEntity } from "./BaseEntity";

export class Message extends BaseEntity {
  sender: string;
  recipient: string;
  message: string;

  public constructor(
    sender?: string,
    recipient?: string,
    message?: string,
    id?: number
  ) {
    super(id);
    this.sender = sender || "";
    this.recipient = recipient || "";
    this.message = message || "";
  }
}
