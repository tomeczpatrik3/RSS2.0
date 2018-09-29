/**
 * A menüpont model
 */
export class MenuItem {
  type: string;
  name: string;
  routerLink: string;
  authorityRequired: string;
  menuItems: MenuItem[];

  public constructor(
    type: string,
    name: string,
    routerLink?: string,
    authorityRequired?: string,
    menuItems?: MenuItem[]
  ) {
    this.type = type;
    this.name = name;
    this.routerLink = routerLink || "";
    this.authorityRequired = authorityRequired || "";
    this.menuItems = menuItems || [];
  }
}
