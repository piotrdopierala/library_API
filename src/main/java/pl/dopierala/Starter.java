package pl.dopierala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.domain.Repository.BookCopyAvailibility;
import pl.dopierala.service.BooksService;

import java.util.Collection;
import java.util.Optional;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    BooksService booksService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello from Spring command line runner.");
        //System.out.println("Generating sample books...");
        //booksService.createSampleBooks();

        // load sample user data from JSON if database is empty



        Collection<BookCopy> allBookCopies = booksService.getAllBookCopies(BookCopyAvailibility.ALL);
        allBookCopies.forEach(System.out::println);

        System.out.println("--------------------------------------");
        Collection<BookDefinition> allBookDefinitions = booksService.getAllBookDefinitions();
        Optional<BookDefinition> pan_tadeusz = allBookDefinitions.stream().filter(bookdef -> bookdef.getTitle().equals("Pan Tadeusz")).findFirst();
        Optional<BookDefinition> dziady = allBookDefinitions.stream().filter(bookdef -> bookdef.getTitle().equals("Dziady")).findFirst();

        if(pan_tadeusz.isPresent()) {
            System.out.println("Count \"Pan Tadeusz\" ALL copies:");
            int count_PT_all = booksService.countCopiesOfBook(pan_tadeusz.get(), BookCopyAvailibility.ALL);
            System.out.println(count_PT_all);
            System.out.println("Count \"Pan Tadeusz\" AVAILABLE copies:");
            int count_PT_avl = booksService.countCopiesOfBook(pan_tadeusz.get(), BookCopyAvailibility.IS_AVAILABLE);
            System.out.println(count_PT_avl);
            System.out.println("Count \"Pan Tadeusz\" BORROWED copies:");
            int count_PT_borwd = booksService.countCopiesOfBook(pan_tadeusz.get(), BookCopyAvailibility.NOT_AVAILABLE_BORROWED);
            System.out.println(count_PT_borwd);
        }


        System.out.println("--- END OF PROGRAM ---");

    }
}
