package cache.dao;

import cache.model.Book;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
