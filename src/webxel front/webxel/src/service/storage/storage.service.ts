import { Injectable } from '@angular/core';

const accessToken = "token";
const USER = "user";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  static getToken() {
    return localStorage.getItem(accessToken);
  }

  static saveAccessToken(token: string): void{
    window.localStorage.removeItem(accessToken);
    window.localStorage.setItem(accessToken, token);
  }
  static saveUser(user:any): void{
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }
  static getUser():any{
   const user = window.localStorage.getItem(USER);
   return user ? JSON.parse(user): null;
  }
  static getUserId(): number{
    const user = this.getUser();
    if(user == null){
      return 0;
    }
    return user.id;
  }
  static getEmail(): string{
    const user = this.getUser();
    if(user == null){
      return "";
    }
    return user.email;
  }

  static getStatus():boolean{

    if(this.getUser() == null){
      return false;
    }
    else if(this.getUser().enabled){
      return true;
    }else{
      return false;
    }
  }

  static getUserRole(): string{
    const user = this.getUser();
    if(user == null){
      return "";
    }
    return user.role;
  }

  static isAdmin():boolean{
    if(this.getUserRole() == "ADMIN"){
      return true;
    }else{
      return false;
    }
  }
  
  static isUser():boolean{
    if(this.getUserRole() == "USER"){
      return true;
    }else{
      return false;
    }
  }
 
  static logout(): void{
    window.localStorage.removeItem(accessToken);
    window.localStorage.removeItem(USER);
    
  }

}
