import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  isLogged = false;
  userName = '';

  constructor(
    private _tokenService: TokenService,
    private _router: Router
  ) { }

  ngOnInit(): void {
    if (this._tokenService.getToken()) {
      this.isLogged = true;
      this.userName = this._tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.userName = '';
    }
  }

  onLogOut(): void {
    this._tokenService.logout();
    this._router.navigate(['/login']);
  }

}
