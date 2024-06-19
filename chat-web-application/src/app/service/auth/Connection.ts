import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
export class Connection{

    public url = 'http://localhost:8080/chat/api';

    constructor(private httpClient: HttpClient){}

    public connect(login: string, password: string): Observable<string> {
        // Logique pour se connecter
        // Par exemple, vous pouvez envoyer une requête HTTP POST au serveur pour authentifier l'utilisateur
        const body = { login, password };
    
        return this.httpClient.post<string>(this.url+"auth/user", {body});
      }

}