import { Component, OnInit } from '@angular/core';
import {UsersService} from '../services/users.service';
import {FormBuilder, FormControl, FormControlName, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [UsersService]

})
export class RegisterComponent implements OnInit {
  [x: string]: any;
  token: string = localStorage.getItem('APP_TOKEN');

  constructor(private _router: Router, private _usersService: UsersService, private _formBuilder: FormBuilder) {
  }



  registerForm = new FormGroup({});
  firstname = new FormControl('');
  lastname = new FormControl('');
  pesel = new FormControl('');
  city = new FormControl('');
  postcode = new FormControl('');
  street = new FormControl('');
  snumber = new FormControl('');
  bnumber = new FormControl('');
  phone = new FormControl('');
  email = new FormControl('');
  password = new FormControl('');

  hide: boolean = true;

  createForm() {
    this.registerForm = this._formBuilder.group({
      firstname: '',
      lastname: '',
      pesel: '',
      city: '',
      postcode: '',
      street: '',
      snumber: '',
      bnumber: '',
      phone: '',
      email: '',
      password: '',
    });
  }
submitForm(form) {
  form.addControl('enabled', new FormControl(true));
  this._usersService.createUser(form.value).subscribe(data => {
    location.reload();
    this._router.navigate(['/login']);
    });
    
}
  ngOnInit() {
    this.createForm();
  
   }
}
