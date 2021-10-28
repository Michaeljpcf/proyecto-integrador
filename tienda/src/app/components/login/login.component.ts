import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUser } from 'src/app/models/login-user';
import { User } from 'src/app/models/user';
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

  login(loginForm) {

    if (loginForm.valid) {
      this.loginUser = new LoginUser(this.userName, this.password);
      this._authService.login_user(this.loginUser).subscribe(
        res=> {
          let payload = JSON.parse(atob(res.token.split(".")[1]));
          this._tokenService.setToken(res.token);
          this._router.navigate(['account/profile']);
          iziToast.show({
            title: 'Success',
            position: 'topRight',
            color: '#A3E1B1',
            timeout: 3000,
            message: `Bienvenido ${payload.sub}`
          });
          console.log(res);

        },
        err=> {
          if (err.status == 401) {
            iziToast.show({
              title: 'Error',
              position: 'topRight',
              color: 'red',
              timeout: 3000,
              message: 'Usuario o contraseña incorrecta'
            });
          }
        }
      );

    } else {
      iziToast.show({
        title: 'Error',
        position: 'bottomRight',
        color: 'red',
        timeout: 3000,
        message: 'Complete todos los datos del formulario.'
      });
    }
  }

}
