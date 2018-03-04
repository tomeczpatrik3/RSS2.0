import { Component } from '@angular/core';
import { NavigationService } from '../../services/navigation.service';
import { MenuItem } from '../../models/MenuItem';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  menuItems: MenuItem[] = [];

  constructor(
    private navigationService: NavigationService
  ) {
    this.menuItems = this.navigationService.getMenu();
  }

  //https://stackoverflow.com/questions/43762984/angular-io-angular-4-how-do-i-refresh-one-component-view-when-triggering-anot

}
