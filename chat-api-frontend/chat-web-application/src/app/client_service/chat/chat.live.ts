import { Component, OnInit } from "@angular/core";
import { ClientService } from "../../service/client/ClientService";
import { ServiceClient } from "../../interface/ServiceClient";
import { User } from "../../interface/User";
import { UserServiceInjection } from "../../service/injection/UserService.injection";
import { ClientServiceInjection } from "../../service/injection/ClientService.injection";
import { ChatList } from "../../interface/chat.list";

@Component({
    selector:'chat-on-direct',
    templateUrl:'./chat.live.html',
    styleUrls: ['./chat.live.scss']
})
export class ChatLive implements OnInit{
    public messages: ServiceClient[]=[]; // Tableau pour stocker les messages
    public userList: User[]=[]
    public chat: ChatList[]=[];
    constructor(private client: ClientService, private userService: UserServiceInjection,
        private clientServiceInjection: ClientServiceInjection){}
        
    public user: User = {
        id: -1,
        nom: "",
        prenom: "",
        dateNaissance: new Date,
        adresse: "",
        email: "",
        login: "",
        motDePasse: ""
    };

    public serviceClient:ServiceClient={
        id:0,
        utilisateurID:0,
        message: "",
        typeContact:"",
        dateContact:new Date,
        status:""
    }; 

    ngOnInit(): void {
        this.userService.getUser().subscribe(value => {this.serviceClient.utilisateurID = value?.id;
            this.user= value
        });
        console.log("User ID: ",this.serviceClient.utilisateurID);

        // Get User conversion list
        this.client.getServiceClient();
        this.getServiceClientConversationList();

        // Get User list
        this.userService.getUserList();
        this.getUserList();

}   

    public sendMessage(){
    
        this.client.sendMessage(this.serviceClient).subscribe(value=>console.log("Service CLient: ",value));
    }

    public getServiceClientConversationList(){
        this.clientServiceInjection.getServiceClient().subscribe(
            values => this.messages = values)
    }

    public getUserList(){
        this.userService.getUserList().subscribe(
            users => this.userList = users
        )
    }

}