export class ReservationCalendarEvent {
  id: number;
  title: string;
  start: string;
  end: string;
  type: string;

  constructor(
    id: number,
    title: string,
    start: string,
    end: string,
    type: string
  ) {
    this.id = id;
    this.title = title;
    this.start = start;
    this.end = end;
    this.type = type;
  }
}
