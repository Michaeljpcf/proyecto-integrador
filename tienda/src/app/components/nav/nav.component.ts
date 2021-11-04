import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  products: Product[] = [];

  userName!: string;

  price:number=0;
  subcat:number=0;
  name:string="";

  constructor(
    private _tokenService: TokenService,
    private _router: Router,
    private _activatedRoute: ActivatedRoute,
    private _clientService: ClientService,
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.userName = this._tokenService.getUserName();
    this._activatedRoute.paramMap.subscribe(()=> {

    });
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

  searchProductName() {
    this._productService.getProductName(this.name).subscribe(
      res=> {
        // this.listProduct = res.list
        this._router.navigateByUrl('search-product');
        this.products = res.list
        console.log(res);
      },
      err=> {
        console.log(err);
      }
    );
  }

  // searchProduct(keyword: string) {
  //   console.log('keyword', keyword);
  //   this._router.navigateByUrl('/search/'+keyword);
  // }

  onLogOut(): void {
    this._tokenService.logout();
    this._router.navigate(['/login']);
  }

}
