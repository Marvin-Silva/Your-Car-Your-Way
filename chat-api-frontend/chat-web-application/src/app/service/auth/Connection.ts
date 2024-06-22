import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginRequest } from "../../interface/LoginRequest";
import { User } from "../../interface/User";
import { UserServiceInjection } from "../injection/UserService.injection";

@Injectable({
    providedIn: 'root'
  })
export class Connection{

    public url = 'http://localhost:8080/chat/api';
    constructor(private httpClient: HttpClient, private userService: UserServiceInjection){}

    public connect(loginRequest: LoginRequest):Observable<User> {
      // console.log("Client", loginRequest);
    const user = this.httpClient.post<User>(this.url+'/auth/user', {login: loginRequest.login, password: loginRequest.password});
    user.subscribe(value => this.userService.setUser(value));
    return user;
    }

    public getUserList():Observable<User[]>{
        const userList = this.httpClient.get<User[]>(this.url+"users");

        userList.subscribe(users => this.userService.setUserList(users));

        return userList;
    }
}