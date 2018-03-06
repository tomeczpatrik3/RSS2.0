import { Component } from '@angular/core';
import { NavigationService } from '../../services/navigation.service';
import { MenuItem } from '../../models/MenuItem';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  menuItems: MenuItem[] = [];

  constructor(
    private navigationService: NavigationService,
    private authService: AuthService
  ) {
    
    this.menuItems = this.navigationService.getMenu();
    
    /*
      Feliratkozunk a Subject-re:
      -- minden egyes változásnál frissítjük a menüt (engedélyeknek megfelelően)
    */
    this.authService.loginStatus.subscribe( 
      res => {
        this.menuItems = this.navigationService.getMenu();
      }
    )
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  //https://stackoverflow.com/questions/43762984/angular-io-angular-4-how-do-i-refresh-one-component-view-when-triggering-anot

}
