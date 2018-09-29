/**
 * A belépéshez szükséges információkat tartalmazó entitás
 */
export class AccountCredentials {
  username: String;
  password: String;

  constructor(username: String, password: String) {
    this.username = username;
    this.password = password;
  }
}
