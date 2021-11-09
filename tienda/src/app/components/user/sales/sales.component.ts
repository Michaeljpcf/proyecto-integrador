import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductOrder } from 'src/app/models/product-order';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  orders: ProductOrder;
  products:Product;

  constructor(
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getRecentOrders();
    this.getProductsSellingByIdSession();
  }

  getRecentOrders() {
    this._productService.getFindByProductIdUser().subscribe(
      data=> {
        console.log(data);
      }
    );
  }

  getProductsSellingByIdSession(){
    this._productService.getProductsSellingByIdSession().subscribe(
      response=>{
        this.products=response;
        console.log(response)
      }
    );
  }

}
