import { Injectable } from "@angular/core";
import { User } from "../../interface/User";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserServiceInjection{

    public user:User={
        id:-1,
        nom:"",
        prenom: "",
        dateNaissance:new Date,
        adresse:"",
        email:"",
        login:"",
        motDePasse:""
    };

    public userList:User[]=[];

    private userSubject = new BehaviorSubject<User>(this.user);
    private userSubjectList = new BehaviorSubject<User[]>(this.userList);
  setUser(user: User) {
    this.userSubject.next(user);
  }

  getUser(): Observable<User> {
    return this.userSubject.asObservable();
  }

  setUserList(user: User[]){
  this.userSubjectList.next(this.userList);
  }

  getUserList():Observable<User[]>{
    return this.userSubjectList.asObservable();
  }
}