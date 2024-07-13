import { Injectable } from "@angular/core";
import { User } from "../../interface/User";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserServiceInjection{

    public userList:User[]=[];
    private userSubjectList = new BehaviorSubject<User[]>(this.userList);

  setUserList(user: User[]):void{
  this.userSubjectList.next(this.userList);
  }

  getUserList():Observable<User[]>{
    return this.userSubjectList.asObservable();
  }
}