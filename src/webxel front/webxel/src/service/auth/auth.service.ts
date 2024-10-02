import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { catchError, Observable, Subscription, tap, throwError, timer } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { StorageService } from '../storage/storage.service';

const API_URL = environment.serverUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  logoutTimer! : Subscription;

  constructor(private http: HttpClient,
              private router: Router,
              private snackbar: MatSnackBar) { }

      

signUp(signupRequest: any): Observable<any>{
 return this.http.post(API_URL+"/api/auth/signup", signupRequest).pipe(
  catchError(this.handleError)
  );
 }
 
 login(loginRequest: any): Observable<any>{
  return this.http.post(API_URL+"/api/auth/login", loginRequest).pipe(
   catchError(this.handleError)
 );
}

logout(): void{
  StorageService.logout();
  this.router.navigateByUrl('/login');
  this.snackbar.open('Logged out due to inactivity', 'Close', { duration: 5000 });

  if(this.logoutTimer){
    this.logoutTimer.unsubscribe();
  }
}

startLogoutTimer(): void{
    
  if(this.logoutTimer){
    this.logoutTimer.unsubscribe();
  
  }
  this.logoutTimer = timer(29*60*1000).pipe(
    tap(() =>{
    this.logout();
    this.router.navigateByUrl("/login");
    })
  ).subscribe();
  }

 handleError(error: HttpErrorResponse): Observable<never> {
  if(error.status === 401){
   return throwError(() => error.message)
  }else if(error.status === 400){
   return throwError(() => error.message);
  }else{
   return throwError(() => error.message);
  }
 }
}
