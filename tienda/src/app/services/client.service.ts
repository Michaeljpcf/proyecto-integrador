import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { SubCategory } from '../models/sub-category';
import { Global } from './global';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  public url;

  constructor(
    private _httpClient: HttpClient
  ) {
    this.url = Global.url;
  }

  listCategories(): Observable<Category[]> {
    return this._httpClient.get<Category[]>(this.url+'categories');      
  }

  listSubCategories(): Observable<SubCategory[]> {
    return this._httpClient.get<SubCategory[]>(this.url+'subcategories');      
  }

  listSubCategoriesId(id: number): Observable<SubCategory[]> {
    return this._httpClient.get<SubCategory[]>(this.url+`subcategories/${id}`);      
  }

}
