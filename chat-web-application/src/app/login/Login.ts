import { Component, OnDestroy } from "@angular/core";
import { Connection } from "../service/auth/Connection";
import { Router } from "@angular/router";
import { LoginRequest } from "../interface/LoginRequest";
import { User } from "../interface/User";
import { Subscription } from "rxjs";

@Component({
    selector: 'app-login',
    templateUrl: './login.html',
    styleUrl: './login.scss'
})
export class Login implements OnDestroy{

    public loginRequest:LoginRequest={login:"", password:""};
    public user:User | any = null;
    public subscription!: Subscription;

    constructor(private con:Connection, private router:Router){}
    
    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
  
    public Authentication():void{
      // console.log("Login credentials : ", this.loginRequest);

      this.subscription = this.con.connect(this.loginRequest)
      .subscribe(value => {
          this.user = value;

          console.log("USER: ",this.user);
          this.router.navigate(['chat']);
      });
    }
}