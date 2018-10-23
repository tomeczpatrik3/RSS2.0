import { BaseEntity } from "./BaseEntity";

/**
 * A foglalásokhoz tartozó ősosztály
 */
export abstract class BaseReservation extends BaseEntity {
  username: string;
  building: string;
  classroom: string;
  status: string;
  note: string;

  public constructor(
    username?: string,
    building?: string,
    classroom?: string,
    status?: string,
    note?: string,
    id?: number
  ) {
    super(id);
    this.username = username || "";
    this.building = building || "";
    this.classroom = classroom || "";
    this.status = status || "";
    this.note = note || "";
  }
}
