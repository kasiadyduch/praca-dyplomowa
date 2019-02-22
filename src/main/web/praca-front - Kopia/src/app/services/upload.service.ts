import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
const options = {
  headers: new HttpHeaders({

  })
};

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  private URL: string = "http://mp-ultra.herokuapp.com/upload/uploadFile";

  constructor(private _http: HttpClient) { }

  uploadFile(file) {
    return this._http.post(this.URL, file, {responseType: 'text'});
  }
}
