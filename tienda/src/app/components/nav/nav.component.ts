import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { ClientService } from 'src/app/services/client.service';
import { ProductService } from 'src/app/services/product.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  categories: Category[] = [];
  listProduct: Product[] = [];

  userName!: string;

  price:number=0;
  subcat:number=0;
  name:string="";

  constructor(
    private _tokenService: TokenService,
    private _router: Router,
    private _clientService: ClientService,
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.userName = this._tokenService.getUserName();
    this.listCategories();
  }

  listCategories() {
    this._clientService.listCategories().subscribe(
      res=> {
        this.categories = res;
      },
      err=> {
        console.log(err);
      }
    );
  }

  searchProductNombre() {
    this._productService.getProductByParams(this.name,this.subcat,this.price).subscribe(
      res=> {
        this.listProduct = res.list
      }
    );
  }

  onLogOut(): void {
    this._tokenService.logout();
    this._router.navigate(['/login']);
  }

}
