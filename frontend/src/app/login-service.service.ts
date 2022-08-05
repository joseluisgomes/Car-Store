import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = "http://localhost:8080/login";

  constructor(private httpClient: HttpClient) { }

  logIn(user: User): Observable<object> {
    console.log(user);
    return this.httpClient.post(`${this.baseUrl}`, user);
  }
}  