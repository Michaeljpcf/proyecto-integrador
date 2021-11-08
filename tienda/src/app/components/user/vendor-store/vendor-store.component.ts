import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-vendor-store',
  templateUrl: './vendor-store.component.html',
  styleUrls: ['./vendor-store.component.css']
})
export class VendorStoreComponent implements OnInit {

  product: Product = new Product();

  constructor(
    private _productService: ProductService,
    private _activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe(
      () => {
        this.getStoreInfo();
      }
    );
  }

  getStoreInfo() {

    const id:number = +this._activatedRoute.snapshot.paramMap.get('id');

    this._productService.getProducts(id).subscribe(
      data=> {
        this.product = data;
        console.log(data);
      }
    );

  }

}
