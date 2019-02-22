import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {httpFactory} from '@angular/http/src/http_module';

const options = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private URL: string = "mp-ultra.herokuapp.com/api/";

  submitBooking(booking) {
    return this._http.post(this.URL + 'bookings/add', booking, options);
  }
  getBookingsByUserId(id) {
    return this._http.get(this.URL + 'booking-details/user/' + id, options);
  }
  deleteBooking(bookingId){
    return this._http.delete(this.URL + 'bookings/' + bookingId, options);
  }
  constructor(private _http: HttpClient) { }
}
