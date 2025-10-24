package cache.dao;

import cache.model.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookRepositoryImpl implements BookRepository {
    private static final long TIME = 3000L;

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Book(isbn, "A book");
    }
}
