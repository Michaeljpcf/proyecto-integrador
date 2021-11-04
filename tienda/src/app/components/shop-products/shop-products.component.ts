import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { SubCategory } from 'src/app/models/sub-category';
import { ClientService } from 'src/app/services/client.service';
import { ProductService } from 'src/app/services/product.service';

declare var jQuery;
declare var $;
declare var noUiSlider:any;
declare var iziToast;

@Component({
  selector: 'app-shop-products',
  templateUrl: './shop-products.component.html',
  styleUrls: ['./shop-products.component.css']
})
export class ShopProductsComponent implements OnInit {

  categories: Category[] = [];
  subCategories: SubCategory[] = [];
  products: Product[] = [];

  name:string="";
  subcat:number=0;
  price:number=0;

  constructor(
    private _clientService: ClientService,
    private _productService: ProductService
  ) { }

  ngOnInit(): void {
    this.mainjs();
    this.listCategories();
    this.listProducts();
  }

  listCategories() {
    this._clientService.listCategories().subscribe(
      res=> {
        this.categories = res;
        // console.log(res);
      },
      err=> {
        // console.log(err);
      }
    );
  }

  listProducts() {
    this._productService.listProducts().subscribe(
      res=> {
        this.products = res;
        // console.log(res);
      }
    );
  }

  filterProduct() {
    this._productService.getProductByParams(this.name,this.subcat,this.price).subscribe(
      res=> {
        this.products = res.list;

        let min = parseInt($('.ps-slider__min').val());
        let max = parseInt($('.ps-slider__max').val());
        console.log(min);
        console.log(max);

        this.products = this.products.filter((item)=>{
          return item.price >= min &&
                 item.price <= max
        });
      },
      err=> {
        console.log(err);
      }
    );
  }













  mainjs() {
    (function($) {
      "use strict";
      // MENU TOGGLE
      function subMenuToggle() {
        $('.menu--mobile .menu-item-has-children > .sub-toggle').on('click', function(e) {
            e.preventDefault();
            var current = $(this).parent('.menu-item-has-children')
            $(this).toggleClass('active');
            current.siblings().find('.sub-toggle').removeClass('active');
            current.children('.sub-menu').slideToggle(350);
            current.siblings().find('.sub-menu').slideUp(350);
            if (current.hasClass('has-mega-menu')) {
                current.children('.mega-menu').slideToggle(350);
                current.siblings('.has-mega-menu').find('.mega-menu').slideUp(350);
            }

        });
        $('.menu--mobile .has-mega-menu .mega-menu__column .sub-toggle').on('click', function(e) {
            e.preventDefault();
            var current = $(this).closest('.mega-menu__column')
            $(this).toggleClass('active');
            current.siblings().find('.sub-toggle').removeClass('active');
            current.children('.mega-menu__list').slideToggle(350);
            current.siblings().find('.mega-menu__list').slideUp(350);
        });
        var listCategories = $('.ps-list--categories');
        if (listCategories.length > 0) {
            $('.ps-list--categories .menu-item-has-children > .sub-toggle').on('click', function(e) {
                e.preventDefault();
                var current = $(this).parent('.menu-item-has-children')
                $(this).toggleClass('active');
                current.siblings().find('.sub-toggle').removeClass('active');
                current.children('.sub-menu').slideToggle(350);
                current.siblings().find('.sub-menu').slideUp(350);
                if (current.hasClass('has-mega-menu')) {
                    current.children('.mega-menu').slideToggle(350);
                    current.siblings('.has-mega-menu').find('.mega-menu').slideUp(350);
                }

            });
        }
      }

      function filterSlider() {
        var nonlinear : any = document.getElementById('nonlinear');
        noUiSlider.create(nonlinear, {
            start: [0, 10000],
            connect: true,
            range: {
                'min': 0,
                'max': 10000
            },
            tooltips: [true,true]
        })

        nonlinear.noUiSlider.on('update', function (values:any) {
          $('.ps-slider__min').val(values[0]);
          $('.ps-slider__max').val(values[1]);
        });
        $('.noUi-tooltip').css('font-size','11px');
      }
      $(function() {
        subMenuToggle();
        filterSlider();
      });

    })(jQuery);
  }



}





