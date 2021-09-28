import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Global } from './global';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  public url;

  constructor(
    private _http: HttpClient
  ) {
    this.url = Global.url;
  }


}
