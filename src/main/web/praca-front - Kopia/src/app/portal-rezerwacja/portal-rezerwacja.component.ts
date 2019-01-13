import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TypesService} from "../services/types.service";

@Component({
  selector: 'app-portal-rezerwacja',
  templateUrl: './portal-rezerwacja.component.html',
  styleUrls: ['./portal-rezerwacja.component.css']
})
export class PortalRezerwacjaComponent implements OnInit {
  selectedFile = null;

  onFileSelected(event) {
    this.selectedFile = event.target.selectedFile[0];
  }

  constructor(private _formBuilder: FormBuilder, private  _typesService: TypesService) { }

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isOptional = false;
  minDate: Date = new Date();
  types: any;
  token: string = localStorage.getItem("APP_TOKEN");
  ngOnInit() {
    console.log(this.token);
    this.firstFormGroup = this._formBuilder.group({
      date: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ''
    });
    this._typesService.getAllTypes(this.token).subscribe(data => {
      this.types = data;
      console.log(this.types);
    });
  }

}
