import { Injectable } from "@angular/core";
import { User } from "../../interface/User";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserService{

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

    private userSubject = new BehaviorSubject<User>(this.user);

  setUser(user: User) {
    this.userSubject.next(user);
  }

  getUser(): Observable<User> {
    return this.userSubject.asObservable();
  }
}