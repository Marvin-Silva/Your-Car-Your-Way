import { Injectable } from "@angular/core";
import { ServiceClient } from "../../interface/ServiceClient";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ClientServiceInjection{
    clients: ServiceClient[]=[];

    private clientServiceSubject = new BehaviorSubject<ServiceClient[]>(this.clients);

    setServiceClient(serviceClient: ServiceClient[]){
        this.clientServiceSubject.next(serviceClient);
    }

    getServiceClient(): Observable<ServiceClient[]>{
        return this.clientServiceSubject.asObservable();
    }
}