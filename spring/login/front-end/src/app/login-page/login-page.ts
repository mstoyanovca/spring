import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { inject } from '@angular/core';
import { User } from '../model/user';
import { AuthenticationService } from '../service/authentication-service';

@Component({
  selector: 'app-login-page',
  imports: [
      FormsModule
  ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
    private service = inject(AuthenticationService);

    user = new User(0, '', '');

    onSubmit() {
        console.log('onSubmit()');
        this.service.login(this.user);
    }
}
