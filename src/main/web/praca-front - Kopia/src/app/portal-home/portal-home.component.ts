import {Component, OnInit} from '@angular/core';
import { UsersService} from '../services/users.service';
@Component({
  selector: 'app-portal-home',
  templateUrl: './portal-home.component.html',
  styleUrls: ['./portal-home.component.css'],
  providers: [UsersService]
})
export class PortalHomeComponent implements OnInit {

  username: string;
  email: string = localStorage.getItem('SUB');

  constructor(private _usersService: UsersService) { }


  logout() {
    localStorage.clear();
  }
  ngOnInit() {
    this._usersService.getByEmail(this.email).subscribe(data => {
      this.username = data['firstname'] + ' ' + data['lastname'];
    });
  }

}
