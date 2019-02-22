import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MailService {
  private URL = 'mp-ultra.herokuapp.com/send/';

  constructor(private _http: HttpClient) { }

public sendMail(type: string, bookingId: string) {
    return this._http.get(this.URL + type + '/' + bookingId);
}
}
