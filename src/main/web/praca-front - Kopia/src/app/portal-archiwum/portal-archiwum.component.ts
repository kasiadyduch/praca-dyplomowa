import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material";
import {BookingService} from '../services/booking.service';

@Component({
  selector: 'app-portal-archiwum',
  templateUrl: './portal-archiwum.component.html',
  styleUrls: ['./portal-archiwum.component.css'],
  providers: [BookingService]
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

  token = localStorage.getItem("APP_TOKEN");

  constructor(private _: BookingService) { }

  ngOnInit() {
  }

}
