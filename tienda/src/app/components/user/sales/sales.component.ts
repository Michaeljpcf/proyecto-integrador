import { Component, OnInit } from '@angular/core';
import { ProductOrder } from 'src/app/models/product-order';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  orders: ProductOrder;

  constructor(
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getRecentOrders();
  }

  getRecentOrders() {
    this._productService.getFindByProductIdUser().subscribe(
      data=> {
        console.log(data);
      }
    );
  }

}
