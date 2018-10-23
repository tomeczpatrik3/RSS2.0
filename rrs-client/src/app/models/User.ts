import { BaseEntity } from "./BaseEntity";

/**
 * A felhasználó entitás
 */
export class User extends BaseEntity {
  username: string;
  password: string;
  name: string;
  email: string;

  public constructor(
    username?: string,
    password?: string,
    name?: string,
    email?: string,
    id?: number
  ) {
    super(id);
    this.username = username || "";
    this.password = password || "";
    this.name = name || "";
    this.email = email || "";
  }
}
