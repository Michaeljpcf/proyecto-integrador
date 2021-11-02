import { Routes, RouterModule } from "@angular/router";
import { ModuleWithProviders } from "@angular/core";
import { InicioComponent } from "./components/inicio/inicio.component";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { ProfileComponent } from "./components/user/profile/profile.component";
import { ShoppingComponent } from "./components/user/shopping/shopping.component";
import { SalesComponent } from "./components/user/sales/sales.component";
import { WishlistComponent } from "./components/user/wishlist/wishlist.component";
import { PostComponent } from "./components/user/post/post.component";
import { ShopProductsComponent } from "./components/shop-products/shop-products.component";

const appRoute: Routes = [
    {path: '', redirectTo: 'index', pathMatch: 'full'},
    {path:'index',component: InicioComponent},
    {path:'login',component:LoginComponent},
    {path:'register', component:RegisterComponent},
    {path:'account/profile', component: ProfileComponent},
    {path:'account/wishlist', component: WishlistComponent},
    {path:'account/shopping', component: ShoppingComponent},
    {path:'account/sales', component: SalesComponent},
    {path:'post', component: PostComponent},
    {path:'shop-products', component: ShopProductsComponent}
]

export const appRoutingProvider: any[]=[];
export const routing: ModuleWithProviders<any> = RouterModule.forRoot(appRoute);
