import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ProductOrder } from 'src/app/models/product-order';
import { ProductService } from 'src/app/services/product.service';

declare const $:any;

@Component({
  selector: 'app-shopping',
  templateUrl: './shopping.component.html',
  styleUrls: ['./shopping.component.css']
})
export class ShoppingComponent implements OnInit, AfterViewInit {

  productOrder: ProductOrder;
  dynamicdata : any;

  constructor(
    private _productService: ProductService
  ) { }

  ngAfterViewInit(): void {
    $('#shopping').DataTable({
      pageLength: 5,
      processing: true,
      search: false,
      lengthMenu : [5, 10, 25]
    });
  }

  ngOnInit(): void {
    this.getProductOrders();
  }

  getProductOrders() {
    this._productService.getFindByProductByIdBuyer().subscribe(
      res=> {
        this.productOrder = res
        console.log(this.productOrder);

      }
    );
  }

}
