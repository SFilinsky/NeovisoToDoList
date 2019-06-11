import { Component, OnInit, Input } from '@angular/core';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  title = 'To Do List App';
  info: any;
  showToken: boolean = false;

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }

  toggleShowToken(): void {
    this.showToken = !this.showToken;
  }

  isAuthorized(): boolean {
    return (this.info != null);
  }

  isNotAuthorized(): boolean {
    return !this.isAuthorized();
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

}
