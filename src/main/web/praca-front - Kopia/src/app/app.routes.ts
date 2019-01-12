import { Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import {PortalHomeComponent} from "./portal-home/portal-home.component";
import {PortalRezerwacjaComponent} from "./portal-rezerwacja/portal-rezerwacja.component";
import { AppComponent } from "./app.component";
import { LayoutComponent } from "./layout/layout.component";
import {LoginGuard} from "./login.guard";
import {AuthGuard} from "./auth.guard";



export const routes: Routes = [
    {path: '', component: LayoutComponent, pathMatch: 'full'},
    {path: 'login' , component: LoginComponent, canActivate: [LoginGuard]},
    {path: 'register', component: RegisterComponent},
    {path: 'portal-home', component: PortalHomeComponent, canActivate: [AuthGuard], children: [
        {path: 'portal-rezerwacja', component: PortalRezerwacjaComponent}
    ]},
    {path: 'portal-rezerwacja', component: PortalRezerwacjaComponent},



];
