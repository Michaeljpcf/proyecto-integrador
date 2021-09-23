import { Routes, RouterModule } from "@angular/router";
import { ModuleWithProviders } from "@angular/core";
import { InicioComponent } from "./components/inicio/inicio.component";
import { LoginComponent } from "./components/login/login.component";
import { AdminGuard } from "./guards/admin.guard";
import { RegisterComponent } from "./components/register/register.component";

const appRoute: Routes = [
    {path:'login',component:LoginComponent},
    {path:'register', component:RegisterComponent/*,canActivate: [AdminGuard], data:{expectedRole:['admin']}*/},
    {path: '', component: InicioComponent}
]

export const appRoutingProvider: any[]=[];
export const routing: ModuleWithProviders<any> = RouterModule.forRoot(appRoute);
