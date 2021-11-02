import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { SubCategory } from 'src/app/models/sub-category';
import { ClientService } from 'src/app/services/client.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-shop-products',
  templateUrl: './shop-products.component.html',
  styleUrls: ['./shop-products.component.css']
})
export class ShopProductsComponent implements OnInit {

  categories: Category[] = [];
  subCategories: SubCategory[] = [];
  products: any;


  constructor(
    private _clientService: ClientService,
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.listCategories();
    this.listProducts();
  }

  listCategories() {
    this._clientService.listCategories().subscribe(
      res=> {
        this.categories = res;
        // console.log(res);
      },
      err=> {
        // console.log(err);
      }
    );
  }

  listProducts() {
    this.products=[];
    this._productService.listProducts().subscribe(
      res=> {
        this.products = res;
        // console.log(res);
      }
    );
  }

}
