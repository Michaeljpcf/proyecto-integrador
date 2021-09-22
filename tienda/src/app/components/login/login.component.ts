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

  loginUser: LoginUser | undefined;
  userName!: string;
  password!: string;

  public token:any = '';

  constructor(
    private _tokenService: TokenService,
    private _authService: AuthService,
    private _router: Router
  ) {
    this.token = this._tokenService.getToken();
  }

  ngOnInit(): void {
    if (this.token) {
      this._router.navigate(['/']);
    }
  }

  login(loginForm:any) {
    if (loginForm.valid) {
      this.loginUser = new LoginUser(this.userName, this.password);
      this._authService.login_user(this.loginUser).subscribe(
        res=> {
          this._tokenService.setToken(res.token);
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
