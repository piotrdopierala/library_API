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
import pl.dopierala.utils.CustomUserDeserializer;
import pl.dopierala.utils.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class Starter implements CommandLineRunner {

    @Autowired
    BooksService booksService;

    @Autowired
    UsersService usersService;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hello from Spring command line runner.");


        // load sample user data from JSON if database is empty
        int usrCount = usersService.countUsers();
        if(usrCount==0){
            Logger.getGlobal().info("Database is empty, reading users from JSON and persising in DB...");
            List<LibraryUser> users = Utils.readUsersFromJson("/sample_user_data.json", new CustomUserDeserializer());
            usersService.saveAllUsers(users);
        }




        System.out.println("--- END OF PROGRAM ---");

    }
}
