import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(
    private _tokenService: TokenService
  ) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let intReq = req;
    /*req.clone({
        setHeaders:{
          'Content-Type':'application/json;charset-utf-8',
          'Accept':'application/json',
          'Authorization':`Beaber ${this._tokenService.getToken()}`,
        },
    });*/

    const token = this._tokenService.getToken();
    if (token != null) {

      intReq = req.clone({headers: req.headers.set('Authorization','Bearer ' + token)});

    }
    return next.handle(intReq);
  }
}

export const interceptorProvider = [{provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true}];
