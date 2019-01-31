import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';

const options = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private URL = 'http://localhost:8080/api/users/';

  getMpAdminUsers(token: string) {
    options.headers.append('Authorization', 'Bearer' + token);
    return this._http.get(this.URL + 'mpAdmin', options);
  }
  getByEmail(email: string) {
    return this._http.get(this.URL +  'email/' + email + '/');
  }
  createUser(user) {
    options.headers.append('Authorization', 'Bearer');
    return this._http.post(this.URL +  'add', user, options );
  }
  constructor(private _http: HttpClient) { }
}
