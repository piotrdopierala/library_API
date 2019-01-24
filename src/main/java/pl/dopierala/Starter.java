package pl.dopierala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;
import pl.dopierala.domain.LibraryUser;
import pl.dopierala.domain.Repository.BookCopyAvailibility;
import pl.dopierala.domain.UserRepository.UserRepository;
import pl.dopierala.service.BooksService;
import pl.dopierala.service.UsersService;
import pl.dopierala.utils.CustomBookDefinitionDeserializer;
import pl.dopierala.utils.CustomUserDeserializer;
import pl.dopierala.utils.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static pl.dopierala.utils.Utils.readBookDefFromJson;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    BooksService booksService;

    @Autowired
    UsersService usersService;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello from Spring command line runner.");


        // load sample user data from JSON if database is empty, save to database
        int usrCount = usersService.countUsers();
        if (usrCount == 0) {
            Logger.getGlobal().info("Database is empty, reading users from JSON and persisting in DB...");
            List<LibraryUser> users = Utils.readUsersFromJson("/sample_user_data.json", new CustomUserDeserializer());
            usersService.saveAllUsers(users);
        }

        // load sample book definitions data from JSON if database is empty, save to database
        int bookDefCount = booksService.countBookDefinitions();
        if (bookDefCount == 0) {
            Logger.getGlobal().info("Database is empty, reading book definitions from JSON and persisting in DB...");
            List<BookDefinition> bookDefinitions = readBookDefFromJson("/sample_book_data.json", new CustomBookDefinitionDeserializer());
            booksService.saveAllBookDefinitions(bookDefinitions);
        }

        bookDefCount = booksService.countBookDefinitions();
        int bookCopiesCount = booksService.countCopiesOfBook(null, BookCopyAvailibility.ALL);

        //create random book copies if definitions exists and no copies. Save generated values to database
        if (bookDefCount > 0 && bookCopiesCount == 0) {
            List<BookDefinition> allBookDefinitions = booksService.getAllBookDefinitions();

            List<BookCopy> generatedBookCopies = Utils.generateRandomBookCopies(allBookDefinitions);
            Logger.getGlobal().info("Generated " + generatedBookCopies.size() + " book copies.");
            booksService.saveAllBookCopies(generatedBookCopies);
        }


        System.out.println("--- END OF PROGRAM ---");

    }
}
