package pl.dopierala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.domain.Repository.BookCopyAvailibility;
import pl.dopierala.domain.Repository.BookCopyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class BooksService {
    @Autowired
    BookCopyRepository bookRepo;

    public Collection<BookCopy> getAllBookCopies(BookCopyAvailibility availability){
        Collection<BookCopy> allBooks = bookRepo.getAllBooksCopies(availability);
        return allBooks;
    }

    public BookCopy getBookCopyById(Integer id){
        return bookRepo.getBookCopyById(id);
    }
    public BookDefinition getBookDefinitionById(Integer id){
        return bookRepo.getBookDefinitionById(id);
    }

    public List<BookDefinition> getAllBookDefinitionsFull(){
        List<BookDefinition> allBookDefinitions = bookRepo.getAllBooksDefinitionsFull();
        return allBookDefinitions;
    }

    public List<BookDefinition> getAllBookDefinitionsLazy(){
        List<BookDefinition> allBookDefLazy = bookRepo.getAllBooksDefinitionsLazy();
        return allBookDefLazy;
    }

    public int countCopiesOfBook(BookDefinition bookDefinition, BookCopyAvailibility bookAvailability){
        return bookRepo.countBookCopies(bookDefinition,bookAvailability);
    }

    public void createSampleBooks(){
        Collection<BookCopy> exampleBookCopies = bookRepo.generateSampleBooks();
        bookRepo.saveAllBooks(exampleBookCopies);
    }

    public int countBookDefinitions() {
        return bookRepo.countBookDefinitions();
    }

    public void saveAllBookDefinitions(List<BookDefinition> bookDefinitions) {
        bookRepo.saveAllBooksDefinitions(bookDefinitions);
    }

    public void saveAllBookCopies(List<BookCopy> bookCopies) {
        bookRepo.saveAllBookCopies(bookCopies);
    }
}
