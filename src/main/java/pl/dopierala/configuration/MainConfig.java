package pl.dopierala.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dopierala.domain.Repository.BookCopyRepository;
import pl.dopierala.domain.Repository.DBBookCopyRepository;

@Configuration
public class MainConfig {

    @Bean
    BookCopyRepository createDBBookRepository(){
        return new DBBookCopyRepository();
    }
}
