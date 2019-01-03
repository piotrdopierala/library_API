package pl.dopierala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.Repository.BookCopyRepository;

import java.util.Collection;

@Service
public class BooksService {
    @Autowired
    BookCopyRepository bookCopyRepo;

    public Collection<BookCopy> getAllBookCopies(){
        Collection<BookCopy> allBooks = bookCopyRepo.getAllBooks();
        return allBooks;
    }

    public void createSampleBooks(){
        Collection<BookCopy> exampleBookCopies = bookCopyRepo.generateSampleBooks();
        bookCopyRepo.saveAllBooks(exampleBookCopies);

    }
}
