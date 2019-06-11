import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent} from '@angular/common/http';
 
import { TokenStorageService } from '../../token-storage.service';
import { MessageService } from '../../message.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private token: TokenStorageService, private messages: MessageService) {}

    intercept(req:  HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        let authReq = req;
        const token = this.token.getToken();
        if (token != null) {
            this.messages.add(`AuthInterceptor: Sending token to server` );
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
        }
        return next.handle(authReq);
    }

}

export const httpInterceptorProviders = [
    
];