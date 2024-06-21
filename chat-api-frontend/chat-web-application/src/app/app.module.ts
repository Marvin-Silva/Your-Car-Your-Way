import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app.routes';
import { ChatLive } from './client_service/chat/chat.live';
import { Message } from './messages/Message';
import { Login } from './login/Login';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
  AppComponent,
  ChatLive,
  Message,
  Login,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
       ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
