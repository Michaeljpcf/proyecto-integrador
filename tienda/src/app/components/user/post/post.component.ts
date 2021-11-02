import { Component, OnInit } from '@angular/core';
import { CreateUrl } from "../../../../assets/js/functions";
import { Category } from 'src/app/models/category';
import { SubCategory } from 'src/app/models/sub-category';
import { ClientService } from 'src/app/services/client.service';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { Byte } from '@angular/compiler/src/util';

declare var jQuery:any;
declare var $:any;
declare var iziToast:any;

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  categories: Category[] = [];
  // subcategories: SubCategory[] = []
  products: Product;

  public imgSrc:string="";
  public idPro:number=34;

  public category:any = {};
  public product:any = {};
  public id:any;


  public file:any | File = undefined;
  public imgSelect: any | ArrayBuffer = 'assets/img/products/default/default-image.jpg';
  public gallery: File[] = [];

  //summernote
  config = {
    placeholder: '',
    tabsize: 2,
    height: 300,
    toolbar: [
      ['misc', ['codeview', 'undo', 'redo']],
      ['style', ['bold', 'italic', 'underline', 'clear']],
      ['para', ['style', 'ul', 'ol', 'paragraph', 'height']],
      ['fontsize', ['fontsize']],
      ['insert', ['table', 'picture', 'link', 'hr']]
    ]
  }

  constructor(
    private _clientService: ClientService,
    private _productService: ProductService,
    private _activatedRoute: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.getImageByIdProduct(this.idPro);
    this.listCategories();
    this._activatedRoute.paramMap.subscribe(
      params=> {
        let id = params.get('id');
        if (id) {
          this._productService.getProducts(id).subscribe(
            res => {
              this.products = res;
            }
          )
        }
      }
    )

  }

  listCategories() {
    this._clientService.listCategories().subscribe(
      res=> {
        this.categories = res;
        this._clientService.listSubCategories().subscribe(
          sub=> {
            console.log(sub);
          }
        );

      },
      err=> {
        console.log(err);

      }
    );
  }

  goProduct() {

    $("#product").addClass("show");

    $("html, body").animate({
      scrollTop: $("#product").offset().top
    })
  }




  validate(input){
    if ($(input).attr("name") == "name") {

      let pattern = /^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]{4,}$/;

      if (!pattern.test(input.value)) {
        $(input).parent().addClass('was-validated');
        return;
      } else {
        this.product.url = CreateUrl.fnc(input.value);
      }
    }

    if ($(input).attr("tag") == "prices") {

      let pattern = /^[.\\,\\0-9]{1,}$/;

      if (!pattern.test(input.value)) {
        $(input).parent().addClass('was-validated');
        return;
      }
    }
  }

  fileChangeEvent(event:any) {
    var file:any;

    this.imgSelect = event.target.files[0]

    if (event.target.files && event.target.files[0]) {
      file = <File>event.target.files[0];
      console.log(file);


    } else {
      iziToast.show({
        title: 'Error',
        position: 'topRight',
        color: 'red',
        message: 'No existe una imagen.'
      });

      $('#image-name').text('Seleccionar imagen');
      this.imgSelect = 'assets/img/products/default/default-image.jpg';
      this.file = undefined;
    }

    if (file.size <= 2000000) {
      if (file.type == 'image/png' || file.type == 'image/webp' || file.type == 'image/jpg' || file.type == 'image/jpeg') {

        const reader = new FileReader();
        reader.onload = e => this.imgSelect = reader.result;
        reader.readAsDataURL(file);

        $('#image-name').text(file.name);

        this.file = file;

      } else {
        iziToast.show({
          title: 'Error',
          position: 'topRight',
          color: 'red',
          message: 'La imagen no tiene el formato(png, webp, jpg, jpeg).'
        });

        $('#image-name').text('Seleccionar imagen');
        this.imgSelect = 'assets/img/products/default/default-image.jpg';
        this.file = undefined;
      }
    } else {
      iziToast.show({
        title: 'Error',
        position: 'topRight',
        color: 'red',
        message: 'La imagen no puede superar los 2MB.'
      });

      $('#image-name').text('Seleccionar imagen');
      this.imgSelect = 'assets/img/products/default/default-image.jpg';
      this.file = undefined;
    }
  }

  //gallery
  onSelect(event) {
		console.log(event);
		this.gallery.push(...event.addedFiles);
	}

	onRemove(event) {
		console.log(event);
		this.gallery.splice(this.gallery.indexOf(event), 1);
	}

  getImageByIdProduct(id:number){
    this._productService.getProductImage(this.idPro).subscribe(
      response => {
        this.imgSrc = response

      },
      (error:any)=>{
        console.log(JSON.stringify(error))

      }
    )
  }


  registerPost(registerForm){
    if (registerForm.valid) {
      console.log(registerForm);

      let formProduct = $(".formProduct");
      let error = 0;

      for (let i = 0; i < formProduct.length; i++) {
        if ($(formProduct[i]).val() == "" || $(formProduct[i]).val() == undefined) {
          error ++;
          $(formProduct[i]).parent().addClass("was-validated");
        }
      }

      if (error > 0) {
        return;
      }

      this._productService.newProduct(this.product,this.gallery).subscribe(
        res=>{
          console.log(res);
        }
      );

      this._productService.uploadImage(this.imgSelect).subscribe(
        products=> {
          this.products = products;
          console.log(products);

        }
      );


    } else {
      iziToast.show({
        title: 'Error',
        position: 'topRight',
        color: 'red',
        message: 'Complete todos los campos obligatorios.'
      });
    }

  }

}
