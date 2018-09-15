import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../authentication/auth.service';

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(
        public authService: AuthService, 
        public router: Router
    ) {}
  
    canActivate(): boolean {
        if (!this.authService.isLoggedIn()) {
            this.router.navigate(['login']);
            return false;
        }
            return true;  
    }
}