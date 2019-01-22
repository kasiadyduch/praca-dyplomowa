import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TypesService} from '../services/types.service';
import {UploadService} from '../services/upload.service';

@Component({
  selector: 'app-portal-rezerwacja',
  templateUrl: './portal-rezerwacja.component.html',
  styleUrls: ['./portal-rezerwacja.component.css'],
  providers: [UploadService]
})
export class PortalRezerwacjaComponent implements OnInit {
  private result: Object;

  constructor(private _formBuilder: FormBuilder, private  _typesService: TypesService, private _uploadService:UploadService) { }

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  isOptional = false;
  minDate = new Date;
  types: any;
  token: string = localStorage.getItem("APP_TOKEN");


  handleFileInput(value) {
    console.log(value);
    this._uploadService.uploadFile(value[0]).subscribe(data => {
      this.result = data;
    });
    console.log(this.result);
  }


  myFilter = (d: Date): boolean => {
    const day = d.getDay();
    return day !== 0 && day !== 6;
  }

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
