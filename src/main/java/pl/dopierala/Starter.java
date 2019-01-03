package pl.dopierala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.service.BooksService;

import java.util.Collection;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    BooksService booksService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello from Spring command line runner.");
        //System.out.println("Generating sample books...");
        //booksService.createSampleBooks();


        Collection<BookCopy> allBookCopies = booksService.getAllBookCopies();
        allBookCopies.forEach(System.out::println);
        System.out.println("--- END OF PROGRAM ---");

    }
}
