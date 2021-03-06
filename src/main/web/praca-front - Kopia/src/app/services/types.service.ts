import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";


const options = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TypesService {

  private URL: string = "http://mp-ultra.herokuapp.com/api/types/";

  getAllTypes(token: string) {
    options.headers.set("Authorization", "Bearer " + token);
  console.log(options.headers.get("Authorization"));
    return this._http.get(this.URL + 'all',options);
  }

  constructor(private _http: HttpClient) { }
}
