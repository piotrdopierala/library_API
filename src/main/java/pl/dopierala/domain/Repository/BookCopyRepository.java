package pl.dopierala.domain.Repository;

import org.springframework.transaction.annotation.Transactional;
import pl.dopierala.domain.BookCopy;

import java.util.Collection;

public interface BookCopyRepository {

    Collection<BookCopy> getAllBooks();

    void saveAllBooks(Collection<BookCopy> bookCopies);

    Collection<BookCopy> generateSampleBooks();
}
