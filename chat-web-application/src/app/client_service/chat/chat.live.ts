import { Component, OnDestroy, OnInit } from "@angular/core";
import { ClientService } from "../../service/client/ClientService";
import { ServiceClient } from "../../interface/ServiceClient";
import { User } from "../../interface/User";
import { UserServiceInjection } from "../../service/injection/UserService.injection";
import { ClientServiceInjection } from "../../service/injection/ClientService.injection";
import { ChatList } from "../../interface/chat.list";
import { Router } from "@angular/router";
import { Subscription } from "rxjs";

@Component({
    selector:'chat-on-direct',
    templateUrl:'./chat.live.html',
    styleUrls: ['./chat.live.scss']
})
export class ChatLive implements OnInit, OnDestroy{
    public messages: ServiceClient[]=[]; // Tableau pour stocker les messages
    public userList: User[]=[]
    public chat: ChatList[]=[];
    private subscription!: Subscription;

    constructor(private client: ClientService, private userService: UserServiceInjection,
        private clientServiceInjection: ClientServiceInjection, private router: Router){}
        
    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
        
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
        this.subscription = this.userService.getUser().subscribe(value => {this.serviceClient.utilisateurID = value?.id;
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

    public sendMessage():void{
    
        this.subscription = this.client.sendMessage(this.serviceClient).subscribe(()=>{

            setTimeout(()=>{
                window.location.reload();
            }, 3000)
        });
    }

    public getServiceClientConversationList():void{
        this.subscription = this.clientServiceInjection.getServiceClient().subscribe(
            values => this.messages = values)
    }

    public getUserList():void{
        this.subscription = this.userService.getUserList().subscribe(
            users => this.userList = users
        )
    }

}