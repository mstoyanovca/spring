import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
    private http = inject(HttpClient);

    login(user: User) {
        console.log("Logging in with email = " + user.email);
        return this.http.post<User>('http://localhost:8080/login', user).subscribe(response => {
            console.log('response = ', response);
        });
    }
}
