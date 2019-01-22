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

  private URL: string = "http://localhost:8080/api/bookin-details/";

  getAllBookings(token: string) {
    options.headers.append('Authorization', 'Bearer' + token);
    return this._http.get(this.URL + 'all', options);
  }
 getTypes(){
    return this._http.get("http://localhost:8080/api/types/all", options);
  }

  constructor(private _http: HttpClient) { }
}
