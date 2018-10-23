import { BaseEntity } from "./BaseEntity";

/**
 * A tanterem entitás
 */
export class Classroom extends BaseEntity {
  name: string;
  hasPC: boolean;
  hasProjector: boolean;
  chairs: number;
  building: string;

  public constructor(
    name?: string,
    hasPC?: boolean,
    hasProjector?: boolean,
    chairs?: number,
    building?: string,
    id?: number
  ) {
    super(id);
    this.name = name || "";
    this.hasPC = hasPC || false;
    this.hasProjector = hasProjector || false;
    this.chairs = chairs || 0;
    this.building = building || "";
  }
}
