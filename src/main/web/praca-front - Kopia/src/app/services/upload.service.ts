import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
const options = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': 'localhost:8080',
    'Access-Control-Allow-Methods': 'POST',
    'Access-Control-Allow-Headers': 'Content-Type, Authorization'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  private URL: string = "http://localhost:8080/api/upload/uploadFile";

  constructor(private _http: HttpClient) { }

  uploadFile(file: File) {
    return this._http.post(this.URL, file, options);
  }
}
