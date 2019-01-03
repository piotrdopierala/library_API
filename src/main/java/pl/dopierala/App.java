package pl.dopierala;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.domain.*;
import pl.dopierala.domain.Repository.DBBookCopyRepository;

import java.util.Collection;

public class App
{

    //TODO: kaskadowanie usuwania kopii ksiazek do definicji i na odwort

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Author.class);
        cfg.addAnnotatedClass(BookDefinition.class);
        cfg.addAnnotatedClass(BookGenere.class);
        cfg.addAnnotatedClass(BookCopy.class);
        cfg.addAnnotatedClass(LibraryUser.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();

        Collection<BookCopy> sampleBooks = DBBookCopyRepository.createSampleBooks();

        currentSession.beginTransaction();
        sampleBooks.forEach(book->currentSession.persist(book));
        //currentSession.persist(sampleBooks);
        currentSession.getTransaction().commit();
    }
}
