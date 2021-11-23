import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductOrder } from 'src/app/models/product-order';
import { ProductService } from 'src/app/services/product.service';

declare var jQuery:any;
declare var $:any;

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  orders: ProductOrder;

  productOrders: ProductOrder = {
    id:0
  }

  constructor(
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getRecentOrders();
    this.updateProccess();
  }

  getRecentOrders() {
    this._productService.getFindByProductIdUser().subscribe(
      data=> {
        this.orders = data;
        console.log(data);
      }
    );
  }

  estadoTracking(item:ProductOrder) {
    this.productOrders = item;
    console.log(this.productOrders.id);
    $("#updateProccess .modal-title span").html(this.productOrders.id);
    $("#updateProccess .modal-body .card .card-body .nameProduct").html(this.productOrders.product_id.name);
    $("#updateProccess .modal-body .card .card-body .priceProduct").html(this.productOrders.product_id.price);

  }

  updateProccess() {
  }

}
