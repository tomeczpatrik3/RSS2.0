/**
 * A menüpont model
 */
export class MenuItem {
  type: string;
  name: string;
  routerLink: string;
  requiredAuthority: string;
  menuItems: MenuItem[];

  public constructor(
    type: string,
    name: string,
    routerLink?: string,
    requiredAuthority?: string,
    menuItems?: MenuItem[]
  ) {
    this.type = type;
    this.name = name;
    this.routerLink = routerLink || "";
    this.requiredAuthority = requiredAuthority || "";
    this.menuItems = menuItems || [];
  }
}
