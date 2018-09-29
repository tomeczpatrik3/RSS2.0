/**
 * A foglalásokhoz tartozó ősosztály
 */
export abstract class BaseReservation {
  username: string;
  building: string;
  classroom: string;
  status: string;
  note: string;

  public constructor(
    username: string,
    building: string,
    classroom: string,
    status: string,
    note: string
  ) {
    this.username = username;
    this.building = building;
    this.classroom = classroom;
    this.status = status;
    this.note = note;
  }
}
