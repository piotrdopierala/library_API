package pl.dopierala.domain.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.dopierala.domain.Author;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBBookCopyRepository implements BookCopyRepository {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public Collection<BookCopy> getAllBooks() {
        String getAllQuery = "FROM BookCopy";
        TypedQuery<BookCopy> query = em.createQuery(getAllQuery, BookCopy.class);
        List<BookCopy> resultList = query.getResultList();

        return resultList;
    }

    @Override
    @Transactional
    public void saveAllBooks(Collection<BookCopy> bookCopies){
        bookCopies.forEach((bookCopy)->em.persist(bookCopy));
    }

    @Override
    public Collection<BookCopy> generateSampleBooks() {
        Collection<BookCopy> booksOnShelf = new ArrayList<>();

        Author am = new Author("Adam", "Mickiewicz");

        BookDefinition panTadeusz = new BookDefinition("Pan Tadeusz", am);
        BookDefinition dziady = new BookDefinition("Dziady", am);
        BookDefinition sonetyKrymskie = new BookDefinition("Sonety krymskie", am);

        booksOnShelf.add(new BookCopy(panTadeusz));
        booksOnShelf.add(new BookCopy(panTadeusz));
        booksOnShelf.add(new BookCopy(panTadeusz));
        booksOnShelf.add(new BookCopy(dziady));
        booksOnShelf.add(new BookCopy(dziady));
        booksOnShelf.add(new BookCopy(dziady));
        booksOnShelf.add(new BookCopy(sonetyKrymskie));

        Author dg = new Author("Dimitry", "Glukhovsky");

        BookDefinition futuRE = new BookDefinition("FUTU.RE", dg);

        for (int i = 0; i < 10; i++) {
            booksOnShelf.add(new BookCopy(futuRE));
        }
        return booksOnShelf;
    }
}
