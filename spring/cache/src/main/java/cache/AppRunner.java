package cache;

import cache.dao.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);
    private final BookRepository bookRepository;

    @Autowired
    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Fetching books...");
        LOGGER.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
        LOGGER.info("isbn-4567 --> {}", bookRepository.getByIsbn("isbn-4567"));
        LOGGER.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
        LOGGER.info("isbn-4567 --> {}", bookRepository.getByIsbn("isbn-4567"));
        LOGGER.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
        LOGGER.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
    }
}
