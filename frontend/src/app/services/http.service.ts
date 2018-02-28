import { Server } from '../config/routes.config';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import {RequestOptions} from '@angular/http';

@Injectable()
export class HttpService {
    constructor(
        private http: HttpClient, 
        private authService: AuthService
    ) {
    }

    post(route: string, params: any, needHeader = true) {
        return this.http.post(
            Server.routeTo(route),
            JSON.stringify(params),
            this.getRequestOptions(needHeader)
        );
    }

    get(route: string, params?: any, needHeader = true) {
        if(params == undefined) {
            params = "";
        }
        return this.http.get(
            Server.routeTo(route) + "/" +  params,
            this.getRequestOptions(needHeader)
        );
    }

    private getRequestOptions(needHeader: boolean) {
        if(!needHeader) {
            return {headers: { } };
        }
        return this.authService.isLoggedIn()
            ? {headers: {'Content-Type':'application/json', 'Authorization': this.authService.getToken()} }
            : {headers: {'Content-Type':'application/json'} };
    }
}