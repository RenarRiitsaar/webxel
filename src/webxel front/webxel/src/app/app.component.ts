import { Component, OnInit } from '@angular/core';
import { StorageService } from '../service/storage/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  isAdminLoggedIn: boolean = StorageService.isAdmin();
  isUserLoggedIn: boolean = StorageService.isUser();

  constructor(private router:Router){}

  ngOnInit(): void {
   this.router.events.subscribe(event => {
      this.isAdminLoggedIn = StorageService.isAdmin();
      this.isUserLoggedIn = StorageService.isUser();
    });
}

  logout(){
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }
}
