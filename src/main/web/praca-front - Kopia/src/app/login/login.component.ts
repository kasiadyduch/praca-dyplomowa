import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {LoginService} from '../services/login.service';
import {Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';
const helper = new JwtHelperService();

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  constructor(private _formBuilder: FormBuilder, private _loginService: LoginService, private _router: Router) { }

  loginForm = new FormGroup({ });
  username = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  password = new FormControl('');

  hide: boolean = true;

  createForm() {
    this.loginForm = this._formBuilder.group({
      username: '',
      password: ''
    });
  }

  // submitForm(form: NgForm) {
  //   console.log(form.value);
  //   this._loginService.doLogin(form.value).subscribe(token => {
  //     localStorage.setItem("APP_TOKEN", token['token']);
  //     this._router.navigate(['/portal-home']);
  //   })
  // }

  getErrorMessage() {
    return this.username.hasError('required') ? 'Wprowadź adres e-mail' :
      this.username.hasError('email') ? 'Niepoprawny e-mail' :
        '';
  }
 submitForm(form: NgForm) {
    this._loginService.doLogin(form.value).subscribe(token => {
      localStorage.setItem("APP_TOKEN", token['token']);
      var decodedToken = helper.decodeToken(token['token']);
      localStorage.setItem("SUB", decodedToken.sub);
      localStorage.setItem("EXP", decodedToken.exp);
      this._router.navigate(['/home']);
    });
 }
  ngOnInit() {
    this.createForm();
  }

}
