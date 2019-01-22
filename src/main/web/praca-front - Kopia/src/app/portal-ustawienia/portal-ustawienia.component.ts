import { Component, OnInit } from '@angular/core';
import { UsersService} from '../services/users.service';


@Component({
  selector: 'app-portal-ustawienia',
  templateUrl: './portal-ustawienia.component.html',
  styleUrls: ['./portal-ustawienia.component.css'],
  providers: [UsersService]
})
export class PortalUstawieniaComponent implements OnInit {
  usernameFirstName: string;
  usernameLastName: string;
  usernameStreet: string;
  usernamePostCode: number;
  usernameSnumber: number;
  usernameBnumber: number;
  usernameCity: string;
  usernamePhone: number;
  email: string = localStorage.getItem('SUB');

  constructor(private _usersService: UsersService) { }


  logout() {
    localStorage.clear();
  }
  ngOnInit() {
    this._usersService.getByEmail(this.email).subscribe(data => {
      this.usernameFirstName = data['firstname'];
      this.usernameLastName = data ['lastname'];
      this.usernameStreet = data ['street'];
      this.usernamePostCode = data ['postcode'];
      this.usernameSnumber = data ['snumber'];
      this.usernameBnumber = data ['bnumber'];
      this.usernameCity = data ['city'];
      this.usernamePhone = data ['phone'];

    });
  }

}
