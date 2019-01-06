package pl.dopierala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.domain.Repository.BookCopyAvailibility;
import pl.dopierala.domain.Repository.BookCopyRepository;

import java.util.Collection;

@Service
public class BooksService {
    @Autowired
    BookCopyRepository bookCopyRepo;

    public Collection<BookCopy> getAllBookCopies(BookCopyAvailibility availability){
        Collection<BookCopy> allBooks = bookCopyRepo.getAllBooksCopies(availability);
        return allBooks;
    }

    public BookCopy getBookCopyById(Integer id){
        return bookCopyRepo.getBookCopyById(id);
    }
    public BookDefinition getBookDefinitionById(Integer id){
        return bookCopyRepo.getBookDefinitionById(id);
    }

    public Collection<BookDefinition> getAllBookDefinitions(){
        Collection<BookDefinition> allBookDefinitions = bookCopyRepo.getAllBooksDefinitions();
        return allBookDefinitions;
    }

    public int countCopiesOfBook(BookDefinition bookDefinition, BookCopyAvailibility bookAvailability){
        return bookCopyRepo.countBookCopies(bookDefinition,bookAvailability);
    }

    public void createSampleBooks(){
        Collection<BookCopy> exampleBookCopies = bookCopyRepo.generateSampleBooks();
        bookCopyRepo.saveAllBooks(exampleBookCopies);
    }
}
