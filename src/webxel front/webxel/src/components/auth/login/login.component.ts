import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../../../service/auth/auth.service';
import { catchError, of, tap } from 'rxjs';
import { StorageService } from '../../../service/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!: FormGroup;
  hidePassword = true;
    static startLogoutTimer: any;

  constructor(private fb: FormBuilder,
  private authService:AuthService,
  private snackbar: MatSnackBar,
  private router:Router){

    this.loginForm = this.fb.group({
      username:[null,[Validators.required]],
      password:[null,[Validators.required]],
    })
  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }
  
  onSubmit(){
    this.authService.login(this.loginForm.value).pipe(
      tap((res)=>{
        if(res.id !== null){
          const user = {
            id: res.id,
            role: res.role,
            enabled:res.enabled
          };

          StorageService.saveUser(user);
          StorageService.saveAccessToken(res.accessToken);
          this.authService.startLogoutTimer();
          
          this.router.navigateByUrl('/table');
          this.snackbar.open('Login successful', 'Close', { duration: 5000 });
        }else{
          this.snackbar.open('Something went wrong', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        }
      }),
      catchError((error) =>{
        this.snackbar.open('Invalid credentials', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        return of(null);
      })
    ).subscribe();
  }
}
