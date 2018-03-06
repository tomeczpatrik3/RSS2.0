export class MenuItem {
    type: string;
    name: string;
    routerLink: string;
    authorityRequired: string;

    public constructor(type: string, name: string, routerLink: string, authorityRequired?: string) {
        this.type = type;
        this.name = name;
        this.routerLink = routerLink;
        this.authorityRequired = authorityRequired || "";
    }
}