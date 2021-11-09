import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductOrder } from 'src/app/models/product-order';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-shopping',
  templateUrl: './shopping.component.html',
  styleUrls: ['./shopping.component.css']
})
export class ShoppingComponent implements OnInit {

  productOrder: ProductOrder = new ProductOrder();
  product: Product = new Product();

  constructor(
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getProductOrders();

  }

  getProductOrders() {
    this._productService.getFindByProductIdUser().subscribe(
      res=> {
        this.productOrder = res
        console.log(this.productOrder);
      }
    );
  }

}
