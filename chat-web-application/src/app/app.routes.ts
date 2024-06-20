import { RouterModule, Routes } from '@angular/router';
import { ChatLive } from './client_service/chat/chat.live';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { Message } from './messages/Message';
import { Login } from './login/Login';

const routes: Routes = [
    {path: '', component: Login},
    { path: 'chat', component: ChatLive },
    {path: 'message', component: Message} // Route par défaut, accède à HomeComponent

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
