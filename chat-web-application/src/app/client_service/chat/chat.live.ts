import { Component, OnInit } from "@angular/core";
import { ClientService } from "../../service/client/ClientService";
import { ServiceClient } from "../../interface/ServiceClient";
import { User } from "../../interface/User";
import { UserService } from "../../service/connection/UserService";

@Component({
    selector:'chat-on-direct',
    templateUrl:'./chat.live.html',
    styleUrls: ['./chat.live.scss']
})
export class ChatLive implements OnInit{

    constructor(private client: ClientService, private userService: UserService){}

    public serviceClient:ServiceClient={
        id:0,
        utilisateurID:0,
        message: "",
        typeContact:"",
        dateContact:new Date,
        status:""
    }; 

    ngOnInit(): void {
        this.userService.getUser().subscribe(value => this.serviceClient.utilisateurID = value?.id);
        console.log("User ID: ",this.serviceClient.utilisateurID);
    }   

    public sendMessage(){

        this.client.sendMessage(this.serviceClient).subscribe(value=>console.log("Service CLient: ",value));
    }

}