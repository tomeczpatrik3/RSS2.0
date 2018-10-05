/**
 * A szemeszter entitás
 */
export class Semester {
  name: string;
  startDate: string;
  endDate: string;

  public constructor(name: string, startDate: string, endDate: string) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
