import { BaseEntity } from "./BaseEntity";

/**
 * Az épület entitás
 */
export class Building extends BaseEntity {
  name: string;

  public constructor(name?: string, id?: number) {
    super(id);
    this.name = name || "";
  }
}
