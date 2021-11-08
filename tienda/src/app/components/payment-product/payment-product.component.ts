import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductOrder } from 'src/app/models/product-order';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-payment-product',
  templateUrl: './payment-product.component.html',
  styleUrls: ['./payment-product.component.css']
})
export class PaymentProductComponent implements OnInit {

  product: Product = new Product();
  payProduct: ProductOrder = new ProductOrder();

  id:any;
  deliveryDate:any;


  constructor(
    private _productService: ProductService,
    private _activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(
      () => {
        this.getProductInfo();
      }
    );
  }

  getProductInfo() {

    const id:number = +this._activatedRoute.snapshot.paramMap.get('id');

    this._productService.getProducts(id).subscribe(
      data=> {
        this.product = data;
        console.log(data);
      }
    );

  }

  paymentProduct(id:number,deliveryDate:ProductOrder) {

    this._productService.checkoutProduct(this.payProduct).subscribe(
      res=> {
        this.product.id = id;
        this.deliveryDate = deliveryDate;
        console.log(res);
      }
    );
  }

}
