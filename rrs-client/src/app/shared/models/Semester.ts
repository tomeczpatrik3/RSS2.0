import { BaseEntity } from "./BaseEntity";

/**
 * A szemeszter entitás
 */
export class Semester extends BaseEntity {
  name: string;
  startDate: string;
  endDate: string;
  opened: boolean;

  public constructor(
    name?: string,
    startDate?: string,
    endDate?: string,
    opened?: boolean,
    id?: number
  ) {
    super(id);
    this.name = name || "";
    this.startDate = startDate || "";
    this.endDate = endDate || "";
    this.opened = opened || false;
  }
}
