package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import rest.model.Quote;
import rest.service.Receiver;

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

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(RestApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        Receiver receiver = ctx.getBean(Receiver.class);
        while (receiver.getCount() == 0) {
            LOGGER.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
            Thread.sleep(500L);
        }

        System.exit(0);
    }
}
