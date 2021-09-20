import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUser } from 'src/app/models/login-user';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

declare var jQuery:any;
declare var $:any;
declare var iziToast;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  isLogged = false;
  isLoginFail = false;
  loginUser: LoginUser;
  userName: string;
  password: string;
  roles: string[] = [];

  constructor(
    private _tokenService: TokenService,
    private _authService: AuthService,
    private _router: Router
  ) { }

  ngOnInit(): void {
    if (this._tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this._tokenService.getAuthorities();
    }
  }

  login(loginForm:any) {
    if (loginForm.valid) {
      this.loginUser = new LoginUser(this.userName, this.password);
      this._authService.login_admin(this.loginUser).subscribe(
        res=> {
          this.isLogged = true;
          this.isLoginFail = false;

          this._tokenService.setToken(res.token);
          this._tokenService.setUserName(res.userName);
          this._tokenService.setAuthorities(res.authorities);
          this.roles = res.authorities;
          this._router.navigate(['/']);
        },
        err=> {
          if (err.status == 401) {
            iziToast.show({
              title: 'Error',
              position: 'topRight',
              color: 'red',
              timeout: 1000,
              message: 'Usuario o contraseña incorrecta'
            });
          }
          this.isLogged = false;
          this.isLoginFail = true;          
        }
      );
      
    } else {
      iziToast.show({
        title: 'Error',
        position: 'topRight',
        color: 'red',
        timeout: 1000,
        message: 'Complete todos los datos del formulario.'
      });
    }
  }

}
