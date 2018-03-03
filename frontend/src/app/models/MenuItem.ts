export class MenuItem {
    name: string;
    routerLink: string;
    authorityRequired: string;

    public constructor(name: string, routerLink: string, authorityRequired?: string) {
        this.name = name;
        this.routerLink = routerLink;
        this.authorityRequired = authorityRequired || "";
    }
}