import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

declare const $:any;

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit, AfterViewInit {

  publications: Product;

  constructor(
    private _productService: ProductService
  ) { }

  ngAfterViewInit(): void {
    $('#publications').DataTable({
      "bLengthChange" : false,
      searching: false,
    });
  }

  ngOnInit(): void {
    this.getProductsSellingByIdSession();
  }

  getProductsSellingByIdSession(){
    this._productService.getProductsSellingByIdSession().subscribe(
      response=>{
        this.publications=response;
        console.log(response)
      }
    );
  }

}
