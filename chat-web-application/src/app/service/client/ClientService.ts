import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ServiceClient } from "../../interface/ServiceClient";
import { Connection } from "../auth/Connection";

@Injectable({
    providedIn: 'root'
})
export class ClientService{

    public url = 'http://localhost:8080/client-service/api';

    constructor(private httpClient: HttpClient){}

    public sendMessage(serviceClient: ServiceClient):Observable<ServiceClient>{

       return this.httpClient.post<ServiceClient>(this.url+"/send-message",{
        id: serviceClient.id,
        utilisateurID: serviceClient.utilisateurID,
        typeContact: serviceClient.typeContact,
        message: serviceClient.message,
        dateContact: serviceClient.dateContact,
        status: serviceClient.status
    });
       
    }
}