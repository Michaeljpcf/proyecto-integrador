import { AfterViewInit, Component, OnInit } from '@angular/core';

declare const $:any;

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngAfterViewInit(): void {
    $('#example').DataTable();
  }

  ngOnInit(): void {
  }

}
