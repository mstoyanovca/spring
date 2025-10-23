package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import rest.model.Quote;

@EnableScheduling
@SpringBootApplication
public class RestApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Quote quote = restTemplate.getForObject("http://localhost:8080/quote", Quote.class);
            LOGGER.info(quote != null ? quote.toString() : "quote == null");
        };
    }

    static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RestApplication.class, args);
    }
}
