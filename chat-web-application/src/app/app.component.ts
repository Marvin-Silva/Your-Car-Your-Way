import { Component } from '@angular/core';
import { Connection } from './service/auth/Connection';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone: true,
  imports: [HttpClientModule] // Import des modules nécessaires
})
export class AppComponent {
  title = 'chat-web-application';

  constructor(private con:Connection){}

  public Authentication(login: string, password: string){
    console.log("Hello wORLD")

    this.con.connect(login,password).subscribe({
      next(value) {
          console.log(`You are logged in as : ${value}`)
      },
    })
  }
}
