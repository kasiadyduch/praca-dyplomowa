import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {BookingService} from '../services/booking.service';
import {UsersService} from '../services/users.service';

@Component({
  selector: 'app-portal-archiwum',
  templateUrl: './portal-archiwum.component.html',
  styleUrls: ['./portal-archiwum.component.css'],
  providers: [BookingService, UsersService]
})
export class PortalArchiwumComponent implements OnInit {

  displayedColumns: string [] = [
    'Lp.',
    'Pacjent',
    'Data',
    'Rodzaj badań',
    'Załącznik'
  ];
  bookings: any;
  dataSource = new MatTableDataSource(null);
  email = localStorage.getItem('SUB');
  userid: number;
  token = localStorage.getItem('APP_TOKEN');

  constructor(private _bookingService: BookingService, private _usersService: UsersService) { }


  ngOnInit() {
    this._usersService.getByEmail(this.email).subscribe(data => {
      this.userid = data ['id'];
    });
    this._bookingService.getBookingsByUserId(5).subscribe(data => {
      this.bookings = data;
      for (let index = 0; index < this.bookings.length; index++) {
        this.bookings[index]['lp'] = index + 1;
      }
      this.dataSource = new MatTableDataSource(this.bookings);
    });
  }


}
