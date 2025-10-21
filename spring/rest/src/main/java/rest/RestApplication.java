package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {
    static void main(String[] args) {
        // navigate to http://localhost:8080/greeting
        // or http://localhost:8080/greeting?name=Martin
        SpringApplication.run(RestApplication.class, args);
    }
}
