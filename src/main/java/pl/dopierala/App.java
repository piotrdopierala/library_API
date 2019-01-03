package pl.dopierala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App
{

    //TODO: kaskadowanie usuwania kopii ksiazek do definicji i na odwort

    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);



//        Configuration cfg = new Configuration();
//        cfg.configure("hibernate.cfg.xml");
//        cfg.addAnnotatedClass(Author.class);
//        cfg.addAnnotatedClass(BookDefinition.class);
//        cfg.addAnnotatedClass(BookGenere.class);
//        cfg.addAnnotatedClass(BookCopy.class);
//        cfg.addAnnotatedClass(LibraryUser.class);
//
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        Collection<BookCopy> sampleBooks = DBBookCopyRepository.generateSampleBooks();
//
//        currentSession.beginTransaction();
//        sampleBooks.forEach(book->currentSession.persist(book));
//        //currentSession.persist(sampleBooks);
//        currentSession.getTransaction().commit();
    }
}
