import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login-service.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  userLogin() {
    console.log(this.user);
    this.loginService.logIn(this.user).subscribe(
      data => { alert("Login Successfully") },
      error => { alert("Sorry, please enter your credentials again") }      
    );
  }
}
