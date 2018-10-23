import { BaseEntity } from "./BaseEntity";

/**
 * A szemeszter entitás
 */
export class Semester extends BaseEntity {
  name: string;
  startDate: string;
  endDate: string;

  public constructor(
    name?: string,
    startDate?: string,
    endDate?: string,
    id?: number
  ) {
    super(id);
    this.name = name || "";
    this.startDate = startDate || "";
    this.endDate = endDate || "";
  }
}
