import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class RestApiService {

  constructor(private http: HttpClient) { }

  public login(username: String, password: String) { // User's credentials
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.http.get("https://localhost:8080/",{headers,responseType: 'text' as 'json'})
  }

  public getAllVehicles() {
    let username = "otibald0@goo.gl"
    let password = "octaviaStand"
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ":" + password)
    });
    return this.http.get("https://localhost:8080/vehicle/list", { headers });
  }
}
