import { BaseEntity } from "./BaseEntity";

/**
 * A tantárgy entitás
 */
export class Subject extends BaseEntity {
  name: string;
  code: string;

  public constructor(name?: string, code?: string, id?: number) {
    super(id);
    this.name = name || "";
    this.code = code || "";
  }
}
