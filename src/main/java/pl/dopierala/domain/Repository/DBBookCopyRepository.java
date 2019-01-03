package pl.dopierala.domain.Repository;

import pl.dopierala.domain.Author;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;

import java.util.ArrayList;
import java.util.Collection;

public class DBBookCopyRepository implements BookCopyRepository {

    @Override
    public Collection<BookCopy> getAllBooks() {
        return null;
    }

    public static Collection<BookCopy> createSampleBooks() {
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
