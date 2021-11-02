import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { ClientService } from 'src/app/services/client.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  categories: Category[] = [];
  userName!: string;
  name!: string;

  constructor(
    private _tokenService: TokenService,
    private _router: Router,
    private _clientService: ClientService,
  ) { }

  ngOnInit(): void {
    this.userName = this._tokenService.getUserName();
    this.listCategories();
  }

  listCategories() {
    this._clientService.listCategories().subscribe(
      res=> {
        this.categories = res;
        this._clientService.listSubCategories().subscribe(
          sub=> {
            // console.log(sub);
          }
        );

      },
      err=> {
        console.log(err);

      }
    );
  }

  onLogOut(): void {
    this._tokenService.logout();
    this._router.navigate(['/login']);
  }

}
