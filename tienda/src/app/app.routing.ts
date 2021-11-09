import { Routes, RouterModule } from "@angular/router";
import { ModuleWithProviders } from "@angular/core";
import { InicioComponent } from "./components/inicio/inicio.component";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { ProfileComponent } from "./components/user/profile/profile.component";
import { ShoppingComponent } from "./components/user/shopping/shopping.component";
import { SalesComponent } from "./components/user/sales/sales.component";
import { PostComponent } from "./components/user/post/post.component";
import { ShopProductsComponent } from "./components/shop-products/shop-products.component";
import { InfoProductComponent } from "./components/info-product/info-product.component";
import { PaymentProductComponent } from "./components/payment-product/payment-product.component";
import { ProductsComponent } from "./components/user/products/products.component";

const appRoute: Routes = [
    {path: '', redirectTo: 'index', pathMatch: 'full'},
    {path:'index',component: InicioComponent},
    {path:'login',component:LoginComponent},
    {path:'register', component:RegisterComponent},
    {path:'account/profile', component: ProfileComponent},
    {path:'account/shopping', component: ShoppingComponent},
    {path:'account/sales', component: SalesComponent},
    {path:'account/publications', component: ProductsComponent},
    {path:'post', component: PostComponent},
    {path:'shop-products', component: ShopProductsComponent},
    {path:'info-product/:id/:url', component: InfoProductComponent},
    {path:'payment-product/:id', component: PaymentProductComponent}
]

export const appRoutingProvider: any[]=[];
export const routing: ModuleWithProviders<any> = RouterModule.forRoot(appRoute);
