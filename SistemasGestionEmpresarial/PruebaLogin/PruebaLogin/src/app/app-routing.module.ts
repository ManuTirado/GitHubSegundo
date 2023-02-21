import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AboutComponent } from './components/about/about.component';
import { AuthGuard } from './services/shared/auth.guard';

const routes: Routes = [
  {path:'', component:LoginComponent,
children:[
  {path: '', redirectTo:'dashboard', pathMatch:'full'},
  {path: 'dashboard', loadChildren:() => import('./components/dashboard/dashboard.component')},
  {path: 'dashboard/about', component: AboutComponent},
]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
