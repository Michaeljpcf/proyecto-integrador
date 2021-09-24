import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  userName!: string;
  name!: string;

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
