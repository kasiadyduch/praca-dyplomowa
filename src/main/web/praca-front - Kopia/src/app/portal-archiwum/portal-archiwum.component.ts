import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {BookingService} from '../services/booking.service';
import {UsersService} from '../services/users.service';
import {MailService} from '../services/mail.service';

@Component({
  selector: 'app-portal-archiwum',
  templateUrl: './portal-archiwum.component.html',
  styleUrls: ['./portal-archiwum.component.css'],
  providers: [BookingService, UsersService, MailService]
})
export class PortalArchiwumComponent implements OnInit {

  displayedColumns: string [] = [
    'Lp.',
    'Pacjent',
    'Data',
    'Rodzaj badań',
    'Załącznik',
    'Usuń',
  ];
  bookings: any;
  dataSource = new MatTableDataSource(null);
  email = localStorage.getItem('SUB');
  userid: number;
  token = localStorage.getItem('APP_TOKEN');
  bookingId: any;

  constructor(private _bookingService: BookingService, private _usersService: UsersService, private _mailService: MailService) {}

  ngOnInit() {
    this._usersService.getByEmail(this.email).subscribe(data => {
      this.userid = data ['id'];
      this._bookingService.getBookingsByUserId(this.userid).subscribe(data => {
        this.bookings = data;
        for (let index = 0; index < this.bookings.length; index++) {
          this.bookings[index]['lp'] = index + 1;
        }
        this.dataSource = new MatTableDataSource(this.bookings);
      });
    });
  }
  cancelBooking(id) {
    this._bookingService.deleteBooking(id).subscribe(data => {
    });
    this._mailService.sendMail('cancel', id).subscribe(data => {
      console.log('TERERERE WŁAŚNIE ANULOWAŁAŚ REZERWACJĘ, WELL DONE!');
      location.reload();
    });
  }
}
