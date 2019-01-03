package pl.dopierala.domain.Repository;

import pl.dopierala.domain.BookCopy;

import java.util.Collection;

public interface BookCopyRepository {
    Collection<BookCopy> getAllBooks();
}
