package pl.dopierala.domain.Repository;

import org.springframework.transaction.annotation.Transactional;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;

import java.util.Collection;

public interface BookCopyRepository {

    Collection<BookCopy> getAllBooksCopies();
    Collection<BookCopy> getAllBooksCopies(BookCopyAvailibility availability);

    @Transactional
    Collection<BookDefinition> getAllBooksDefinitions();

    void saveAllBooks(Collection<BookCopy> bookCopies);

    Collection<BookCopy> generateSampleBooks();

    int countBookCopies(BookDefinition bookDefinitionToCount, BookCopyAvailibility availability);

    @Transactional
    void addBookCopy(BookCopy bookCopyToAdd);

    @Transactional
    void addBookDefinition(BookDefinition bookDefinitionToAdd);

    @Transactional
    BookCopy getBookCopyById(Integer id);

    @Transactional
    BookDefinition getBookDefinitionById(Integer id);
}
