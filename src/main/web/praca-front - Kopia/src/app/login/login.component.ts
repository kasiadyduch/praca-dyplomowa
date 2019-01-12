import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {LoginService} from "../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
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

  submitForm(form: NgForm) {
    console.log(form.value);
    this._loginService.doLogin(form.value).subscribe(token => {
      localStorage.setItem("APP_TOKEN", token['token']);
      this._router.navigate(['/portal-home']);
    })
  }

  getErrorMessage() {
    return this.username.hasError('required') ? 'Wprowadź adres e-mail' :
      this.username.hasError('email') ? 'Niepoprawny e-mail' :
        '';
  }

  ngOnInit() {
    this.createForm();
  }

}
