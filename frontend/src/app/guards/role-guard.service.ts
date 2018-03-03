import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { AuthService } from '../authentication/auth.service';


@Injectable()
export class RoleGuardService implements CanActivate {
    constructor(
        public authService: AuthService, 
        public router: Router
    ) {}
  
    canActivate(route: ActivatedRouteSnapshot): boolean {
        // this will be passed from the route config
        // on the data property
        const authority = route.data.authority;
        const token = localStorage.getItem('token');

        if ( !this.authService.isLoggedIn() || 
                this.authService.getAuthorities().indexOf(authority) == -1) {
            //this.router.navigate(['login']); <-- redirect to..
            return false;
        }
            return true;
    }
}