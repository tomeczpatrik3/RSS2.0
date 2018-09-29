/**
 * A felhasználó entitás
 */
export class User {
  username: string;
  password: string;
  name: string;
  email: string;

  public constructor(
    username?: string,
    password?: string,
    name?: string,
    email?: string
  ) {
    this.username = username || "";
    this.password = password || "";
    this.name = name || "";
    this.email = email || "";
  }
}
