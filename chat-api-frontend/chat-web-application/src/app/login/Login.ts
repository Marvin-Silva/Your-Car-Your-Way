import { Component } from "@angular/core";
import { Connection } from "../service/auth/Connection";
import { Router } from "@angular/router";
import { LoginRequest } from "../interface/LoginRequest";
import { User } from "../interface/User";

@Component({
    selector: 'app-login',
    templateUrl: './login.html',
    styleUrl: './login.scss'
})
export class Login{

    public loginRequest:LoginRequest={login:"", password:""};
    public user:User | any = null;

    constructor(private con:Connection, private router:Router){}
  
    public Authentication(){
      // console.log("Login credentials : ", this.loginRequest);

      this.con.connect(this.loginRequest)
      .subscribe(value => {
          this.user = value;

          console.log("USER: ",this.user);
          this.router.navigate(['chat']);
      });
    }
}