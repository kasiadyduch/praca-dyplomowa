import { Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
cookieValue = 'UNKNOWN';

constructor (private _cookieService: CookieService) {}

ngOnInit(): void {
  this._cookieService.set('Test', 'Hello World');
}

}
