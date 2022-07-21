import { Component, OnInit } from '@angular/core';
import { RestApiService } from '../rest-api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  message: any;

  constructor(private service: RestApiService) { }

  ngOnInit(): void { }

  performLogin(): void {
    let response = this.service.login(this.username, this.password);
    response.subscribe(data => { console.log(data) });
  }
}
