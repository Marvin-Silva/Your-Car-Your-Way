import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginRequest } from "../../interface/LoginRequest";
import { User } from "../../interface/User";

@Injectable({
    providedIn: 'root'
  })
export class Connection{

    public url = 'http://localhost:8080/chat/api';
    public user!:Observable<User>;
    constructor(private httpClient: HttpClient){}

    public connect(loginRequest: LoginRequest):Observable<User> {
      // console.log("Client", loginRequest);
    this.user = this.httpClient.post<User>(this.url+'/auth/user', {login: loginRequest.login, password: loginRequest.password});
    // user.subscribe(value => this.userService.setUser(value));
    return this.user;
    }

    public getUser():Observable<User>{
      return this.user;
    }
    public getUserList():Observable<User[]>{
        return this.httpClient.get<User[]>(this.url+"users");
    }
}