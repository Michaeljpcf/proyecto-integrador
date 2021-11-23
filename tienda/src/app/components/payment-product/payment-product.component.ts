import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductOrder } from 'src/app/models/product-order';
import { User } from 'src/app/models/user';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-payment-product',
  templateUrl: './payment-product.component.html',
  styleUrls: ['./payment-product.component.css']
})
export class PaymentProductComponent implements OnInit {

  product: Product = new Product();
  user: User = new User();

  payProduct: ProductOrder = new ProductOrder();

  constructor(
    private _productService: ProductService,
    private _activatedRoute: ActivatedRoute,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(
      () => {
        this.getProductInfo();
      }
    );
    this.getUserByIdSession();
    console.log(this.payProduct.product_id);

  }

  getProductInfo() {

    const id:number = +this._activatedRoute.snapshot.paramMap.get('id');

    this._productService.getProducts(id).subscribe(
      data=> {
        this.product = data;
        console.log(data);

        this.payProduct={
          deliveryDate:this.product.deliveryRangeDate,
          total:this.product.price,
          product_id:this.product
        }
        console.log(this.payProduct)

      }
    );

  }

  paymentProduct() {
    this._productService.checkoutProduct(this.payProduct).subscribe(
      res=> {
        console.log(res);
        this._router.navigate(['account/shopping']);
      }
    );
  }

  getUserByIdSession() {
    this._productService.getUserByIdSession().subscribe(
      data=> {
        this.user = data;
        console.log(data);
      }
    );
  }

}
