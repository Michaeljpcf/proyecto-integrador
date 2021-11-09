import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../models/product';
import { environment } from 'src/environments/environment';
import { ProductOrder } from '../models/product-order';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public url;

  constructor(
    private _httpClient: HttpClient
  ) {
    this.url = environment.apiUrl;
  }

  listProducts(): Observable<any> {
    return this._httpClient.get(this.url+'/getListProductsWithImage');
  }

  getProducts(id): Observable<Product> {
    return this._httpClient.get<Product>(`${this.url}/findProduct/${id}`);
  }

  updateProduct(product: Product): Observable<any> {
    return this._httpClient.put<any>(this.url+'/updateProduct', product);
  }

  deleteProduct(id: number): Observable<any> {
    return this._httpClient.delete<any>(this.url+`/product/${id}`);
  }

  uploadImage(file: File, id:any): Observable<HttpEvent<{}>>{
    let formData = new FormData();
    formData.append("file", file);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.url}/products/upload/`,formData, {
      reportProgress: true
    });
    return this._httpClient.request(req);
  }

  newProduct(obj:Product,images: File[]): Observable<any> {
    const formData = new FormData();

    images.forEach(file=>formData.append('images',file));

    formData.append('obj',new Blob([JSON.stringify(obj)],{type:'application/json'}));

    return this._httpClient.post<any>(this.url+'/newProductt', formData);
  }

  getProductImage(idProduct:number){
    return this._httpClient.get(this.url+`/getImgProductByProductId/${idProduct}`,{responseType:'text'});
  }

  getProductByParams(name:string,cat:number,subCat:number,price1:number,price2:number):Observable<any>{
    const params = new HttpParams()
      .set("name",name)
      .set("cat",cat)
      .set("subCat",subCat)
      .set("price1",price1)
      .set("price2",price2);

      return this._httpClient.get(this.url+"/getListProductUsingParams",{params})
  }

  getProductName(name:string):Observable<any>{
    const params = new HttpParams()
      .set("name",name);

      return this._httpClient.get(this.url+"/getListProductUsingParams",{params})
  }

  checkoutProduct(obj:ProductOrder): Observable<ProductOrder> {
    return this._httpClient.post<ProductOrder>(this.url+'/newOrderProduct',obj);
  }

  getFindByProductByIdBuyer(): Observable<ProductOrder> {
    return this._httpClient.get<ProductOrder>(this.url+"/findByProductByIdBuyer");
  }

  getFindByProductIdUser(): Observable<ProductOrder> {
    return this._httpClient.get<ProductOrder>(this.url+"/findByProductIdUser");
  }

  getUserByIdSession(): Observable<User> {
    return this._httpClient.get<User>(this.url+"/getUserByIdSession");
  }

  updateUserByIdSession(user: User): Observable<User> {
    return this._httpClient.put<User>(this.url+"/UpdateUserByUser",user);
  }

  getProductsSellingByIdSession(): Observable<Product> {
    return this._httpClient.get<Product>(this.url+"/getProductsByIdSession");
  }

}


