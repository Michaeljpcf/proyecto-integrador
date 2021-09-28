import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

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
import { SidebarComponent } from './components/user/sidebar/sidebar.component';
import { WishlistComponent } from './components/user/wishlist/wishlist.component';
import { SellComponent } from './components/user/sell/sell.component';

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
    SidebarComponent,
    WishlistComponent,
    SellComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    routing
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
