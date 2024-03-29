import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { TokenService } from '../services/token.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  realRole: string;

  constructor(
    private _tokenService: TokenService,
    private _router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    const expectedRole = route.data.expectedRole;
    this.realRole = this._tokenService.isAdmin() ? 'admin' : 'user';
    
    if (!this._tokenService.isLogged() || expectedRole.indexOf(this.realRole) < 0) {
      this._router.navigate(['/login']);
      return false;
    }
    return true;
  }
  
}
