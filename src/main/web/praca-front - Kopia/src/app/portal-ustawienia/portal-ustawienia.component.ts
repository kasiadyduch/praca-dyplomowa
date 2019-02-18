import { Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users.service';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';



@Component({
  selector: 'app-portal-ustawienia',
  templateUrl: './portal-ustawienia.component.html',
  styleUrls: ['./portal-ustawienia.component.css'],
  providers: [UsersService]
})
export class PortalUstawieniaComponent implements OnInit {


  editionForm = new FormGroup({});

  firstname = new FormControl('');
  lastname = new FormControl('');
  street = new FormControl('');
  postcode = new FormControl('');
  snumber = new FormControl('');
  bnumber = new FormControl('');
  city = new FormControl('');
  phone = new FormControl('');
  emailFromLocalStorage = localStorage.getItem('SUB');
  userId;
  userPesel;
  userPassword;
  user;
  userEmail;

  createForm() {
    this.editionForm = this._formBuilder.group({
      firstname: '',
      lastname: '',
      street: '',
      postcode: '',
      snumber: '',
      bnumber: '',
      city: '',
      phone: '',
      password: ''
    });
  }

  submitForm(form) {
    form.addControl('pesel', new FormControl(this.userPesel));
    form.addControl('password', new FormControl(this.userPassword));
    form.addControl('email', new FormControl(this.userEmail));
  

    this._usersService.updateUser(this.userId, form.value).subscribe(data => {
    this._router.navigate(['/portal-home/portal-glowna']);
    console.log(form.value);
    });
    location.reload();
  }



  constructor(private _usersService: UsersService, private _formBuilder: FormBuilder,
    private _router: Router) {
  }

  logout() {
    localStorage.clear();
  }
  ngOnInit() {
    this.createForm();
    this._usersService.getByEmail(this.emailFromLocalStorage).subscribe(data => {
      this.user = data;

      this.editionForm = this._formBuilder.group({
        firstname: data['firstname'],
        lastname: data['lastname'],
        street: data['street'],
        postcode: data['postcode'],
        snumber: data['snumber'],
        bnumber: data['bnumber'],
        city: data['city'],
        phone: data['phone']
      });


      this.userId = data['id'];
      this.userPesel = data['pesel'];
      this.userPassword = data['password'];
      this.userEmail = data['email'];
      console.log(this.editionForm.value);

    });
  }

}
