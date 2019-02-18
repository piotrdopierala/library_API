package pl.dopierala.domain.Repository;

import org.springframework.transaction.annotation.Transactional;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;

import java.util.Collection;
import java.util.List;

public interface BookCopyRepository {

    Collection<BookCopy> getAllBooksCopies();
    Collection<BookCopy> getAllBooksCopies(BookCopyAvailibility availability);
    Collection<BookCopy> getBookCopies(Integer id, BookCopyAvailibility availability);

    @Transactional
    List<BookDefinition> getAllBooksDefinitionsFull();

    @Transactional
    List<BookDefinition> getAllBooksDefinitionsLazy();

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

    int countBookDefinitions();

    void saveAllBooksDefinitions(List<BookDefinition> bookDefinitions);

    void saveAllBookCopies(List<BookCopy> bookCopies);



}
