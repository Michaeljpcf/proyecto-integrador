import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { NgxSummernoteModule } from "ngx-summernote";
import { NgxDropzoneModule } from 'ngx-dropzone';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { routing } from "./app.routing";
import { InicioComponent } from './components/inicio/inicio.component';
import { NavComponent } from './components/nav/nav.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { interceptorProvider } from './services/interceptor.service';
import { ProfileComponent } from './components/user/profile/profile.component';
import { SalesComponent } from './components/user/sales/sales.component';
import { ShoppingComponent } from './components/user/shopping/shopping.component';
import { TopbarComponent } from './components/user/topbar/topbar.component';
import { WishlistComponent } from './components/user/wishlist/wishlist.component';
import { PostComponent } from './components/user/post/post.component';
import { ShopProductsComponent } from './components/shop-products/shop-products.component';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    NavComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    SalesComponent,
    ShoppingComponent,
    TopbarComponent,
    WishlistComponent,
    PostComponent,
    ShopProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    routing,
    NgxSummernoteModule,
    NgxDropzoneModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
