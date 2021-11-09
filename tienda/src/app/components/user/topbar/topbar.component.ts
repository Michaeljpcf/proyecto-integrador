import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  user: User = new User();

  constructor(
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getUserByIdSession();
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
