package rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.model.Quote;
import rest.model.Value;

@RestController
public class QuoteController {
    @GetMapping("/quote")
    public Quote quote() {
        return new Quote("success", new Value(10L, "Really loving Spring Boot"));
    }
}
