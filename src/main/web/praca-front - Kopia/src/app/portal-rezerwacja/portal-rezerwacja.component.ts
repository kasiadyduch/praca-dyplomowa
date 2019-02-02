import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormControlName, FormGroup, Validators} from '@angular/forms';
import {TypesService} from '../services/types.service';
import {UploadService} from '../services/upload.service';
import {UsersService} from '../services/users.service';
import {DatePipe} from '@angular/common';
import {BookingService} from '../services/booking.service';
import {MailService} from '../services/mail.service';

@Component({
  selector: 'app-portal-rezerwacja',
  templateUrl: './portal-rezerwacja.component.html',
  styleUrls: ['./portal-rezerwacja.component.css'],
  providers: [UploadService, UsersService, DatePipe, BookingService, MailService]
})
export class PortalRezerwacjaComponent implements OnInit {
  filePath;
  constructor(private _formBuilder: FormBuilder, private  _typesService: TypesService, private _uploadService: UploadService,
              private _usersService: UsersService, private _datePipe: DatePipe, private _bookingService: BookingService,
              private  _mailService: MailService) { }
  email: string = localStorage.getItem('SUB');
  minDate = new Date;
  types: any;
  token: string = localStorage.getItem('APP_TOKEN');
  userId: number;
  bookingId: any;

  bookingForm = new FormGroup({ });
  // userid = new FormControl(this.userId);
  typeid = new FormControl();
  date = new FormControl();
  // attachmentpath = new
  createForm() {
    this.bookingForm = this._formBuilder.group({
      // userid: this.userId,
      typeid: '',
      date: '',
    });
  }
  submitForm(form) {
    form.addControl('userid', new FormControl(this.userId));
    form.addControl('attachmentpath', new FormControl(this.filePath));
    console.log(this.filePath);
    const tempDate = form.controls['date'].value;
    const formattedDate = this._datePipe.transform(tempDate, 'yyyy-MM-dd');
    form.patchValue({
      'date' : formattedDate
    });
    console.log(form.value);
    this._bookingService.submitBooking(form.value).subscribe(data => {
      this.bookingId = data;
      console.log(this.bookingId);
      this._mailService.sendMail(this.bookingId).subscribe(next => {
        console.log('TO JA!');
      }, error => {
        console.log('Coś poszło nie tak :/');
      });
    });
  }
  handleFileInput(value) {
    console.log(value);
    const formData = new FormData();
    formData.append('file', value[0]);
    this._uploadService.uploadFile(formData).subscribe(data => {
      this.filePath = data;
    });

  }
  myFilter = (d: Date): boolean => {
    const day = d.getDay();
    return day !== 0 && day !== 6;
  }

  ngOnInit() {
    this.createForm();
    this._typesService.getAllTypes(this.token).subscribe(data => {
      this.types = data;
      console.log(this.types);
    });
    this._usersService.getByEmail(this.email).subscribe(data => {
      this.userId = data['id'];
      console.log(this.userId);
    });
  }

}
