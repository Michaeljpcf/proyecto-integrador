import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userName: string;

  constructor(
    private _tokenService: TokenService,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this.userName = this._tokenService.getUserName();
  }

  onLogOut(): void {
    this._tokenService.logout();
    this._router.navigate(['/login']);
  }

}
