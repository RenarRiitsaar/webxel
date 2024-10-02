import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../components/auth/login/login.component';
import { SignupComponent } from '../components/auth/signup/signup.component';
import { TableComponent } from '../components/table/table.component';

const routes: Routes = [
  {path: "login", component : LoginComponent},
  {path: "register", component : SignupComponent},
  {path: "table", component : TableComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
