import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const options = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private URL: string = "mp-ultra.herokuapp.com/auth";

  doLogin(loginForm: any) {
    return this._http.post(this.URL, loginForm, options);
  }

  constructor(private _http: HttpClient) { }
}
