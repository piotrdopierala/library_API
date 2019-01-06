package pl.dopierala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class App
{

    //TODO: kaskadowanie usuwania kopii ksiazek do definicji i na odwort

    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);

    }
}
