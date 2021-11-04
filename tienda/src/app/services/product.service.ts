import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../models/product';
import { environment } from 'src/environments/environment';

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
    return this._httpClient.get<Product>(`${this.url}+/'findProduct'/+${id}`);
  }

  /*newProduct(product: Product, images:any): Observable<any> {
    const params = new HttpParams()
      .set("images",images);
    return this._httpClient.post<any>(this.url+'newProductt', product,{params});
  }*/

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

    // return this._httpClient.post(`${this.url}/products/upload/`, formData).pipe(
    //   map((response:any) => response.product as Product),
    //   catchError(e => {
    //     console.log(e.error.mensaje);
    //     return throwError(e);
    //   })

    // );
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

  getProductByParams(name:string,subcat:number,price:number):Observable<any>{
    const params = new HttpParams()
      .set("name",name)
      .set("cat",subcat)
      .set("price",price);

      return this._httpClient.get(this.url+"/getListProductUsingParams",{params})
  }

  getProductName(name:string):Observable<any>{
    const params = new HttpParams()
      .set("name",name);

      return this._httpClient.get(this.url+"/getListProductUsingParams",{params})
  }

  // searchProducts(keyword: string): Observable<Product[]> {
  //   const searchUrl = `${this.url}/search/categ`;
  //   return this._httpClient.get<Product[]>(searchUrl).pipe(
  //     map(response => response)
  //   )
  // };

}


