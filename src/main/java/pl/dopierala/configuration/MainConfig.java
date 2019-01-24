package pl.dopierala.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dopierala.domain.Repository.BookCopyRepository;
import pl.dopierala.domain.Repository.DBBookCopyRepository;
import pl.dopierala.domain.UserRepository.DBUserRepository;
import pl.dopierala.domain.UserRepository.UserRepository;

@Configuration
public class MainConfig {

    @Bean
    BookCopyRepository createDBBookRepository() {
        return new DBBookCopyRepository();
    }

    @Bean
    UserRepository createDBUserRepository() {
        return new DBUserRepository();
    }

}
