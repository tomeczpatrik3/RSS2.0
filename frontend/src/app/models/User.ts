export class User {
    username: string;
    password: string;
    name: string;
    email: string;
    role: string;

    public constructor (username: string, password: string, name: string, email: string, role: string) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}