import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JwtDTO } from '../models/jwt-dto';
import { LoginUser } from '../models/login-user';
import { NewUser } from '../models/new-user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  authURL = environment.authURL;

  constructor(private httpClient: HttpClient) { }

  public new(newUser: NewUser): Observable<any> {
    return this.httpClient.post<any>(this.authURL + 'new', newUser);
  }

  public login_admin(loginUser: LoginUser): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', loginUser);
  }
}
