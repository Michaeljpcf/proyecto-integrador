import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Product } from '../models/product';
import { Global } from './global';
import { map, catchError } from "rxjs/operators";
import { Byte } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public url;

  constructor(
    private _httpClient: HttpClient
  ) {
    this.url = Global.url;
  }

  listProducts(): Observable<Product[]> {
    return this._httpClient.get<Product[]>(this.url+'products');
  }

  getProducts(id): Observable<Product> {
    return this._httpClient.get<Product>(`${this.url}+/'findProduct'/+${id}`);
  }

  /*newProduct(product: Product, images:any): Observable<any> {
    const params = new HttpParams()
      .set("images",images);
    return this._httpClient.post<any>(this.url+'newProductt', product,{params});
  }*/

  updateProduct(product: Product): Observable<any> {
    return this._httpClient.put<any>(this.url+'updateProduct', product);
  }

  deleteProduct(id: number): Observable<any> {
    return this._httpClient.delete<any>(this.url+`product/${id}`);
  }

  uploadImage(file: File): Observable<Product>{
    let formData = new FormData();
    formData.append("file", file);
    return this._httpClient.post(`${this.url}products/upload/`, formData).pipe(
      map((response:any) => response.product as Product),
      catchError(e => {
        console.log(e.error.mensaje);
        return throwError(e);
      })

    );
  }



  newProduct(obj:Product,images: File[]): Observable<any> {
    const formData = new FormData();

    images.forEach(file=>formData.append('images',file));

    formData.append('obj',new Blob([JSON.stringify(obj)],{type:'application/json'}));

    return this._httpClient.post<any>(this.url+'newProductt', formData);
  }


  getProductImage(idProduct:number){
    return this._httpClient.get(this.url+`getImgProductByProductId/${idProduct}`,{responseType:'text'});
  }


}
