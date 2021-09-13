import { Component, OnInit } from '@angular/core';

declare var jQuery:any;
declare var $:any;
declare var iziToast;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user: any = {}

  constructor() { }

  ngOnInit(): void {
  }

  login(loginForm:any) {
    if (loginForm.valid) {
      console.log(this.user);
      
    } else {
      iziToast.show({
        title: 'Error',
        position: 'topRight',
        color: 'red',
        timeout: 1000,
        message: 'Los datos del formulario no son válidos.'
      });
    }
  }

}
