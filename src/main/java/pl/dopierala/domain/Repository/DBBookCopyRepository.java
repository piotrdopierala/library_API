package pl.dopierala.domain.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.dopierala.domain.Author;
import pl.dopierala.domain.BookCopy;
import pl.dopierala.domain.BookDefinition;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class DBBookCopyRepository implements BookCopyRepository {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public Collection<BookCopy> getAllBooksCopies() {
        String getAllQuery = "FROM BookCopy";
        TypedQuery<BookCopy> query = em.createQuery(getAllQuery, BookCopy.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Collection<BookCopy> getAllBooksCopies(BookCopyAvailibility availability) {
        String queryString="";
        if (Objects.nonNull(availability)) {
            switch (availability) {
                case ALL:
                default:
                    queryString = "FROM BookCopy";
                    break;
                case IS_AVAILABLE:
                    queryString = "FROM BookCopy WHERE isAvailable=true";
                    break;
                case NOT_AVAILABLE_BORROWED:
                    queryString = "FROM BookCopy WHERE isAvailable=false";
                    break;
            }
        } else {
            queryString = "FROM BookCopy";
        }

        TypedQuery<BookCopy> query = em.createQuery(queryString, BookCopy.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<BookDefinition> getAllBooksDefinitions(){
        String getAllQuery = "FROM BookDefinition bd LEFT JOIN FETCH bd.copies";
        TypedQuery<BookDefinition> query = em.createQuery(getAllQuery, BookDefinition.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveAllBooks(Collection<BookCopy> bookCopies) {
        bookCopies.forEach((bookCopy) -> em.persist(bookCopy));
    }

    /** Function generates sample books in library
     *
     * @deprecated from 23.01.2019 now reading sample data from JSON
     * @return Collection of generated books
     */
    @Override
    @Deprecated
    public Collection<BookCopy> generateSampleBooks() {
        Collection<BookCopy> booksCopiesList = new ArrayList<>();

        Author am = new Author("Adam", "Mickiewicz");

        BookDefinition panTadeusz = new BookDefinition("Pan Tadeusz", am);
        BookDefinition dziady = new BookDefinition("Dziady", am);
        BookDefinition sonetyKrymskie = new BookDefinition("Sonety krymskie", am);

        booksCopiesList.add(new BookCopy(panTadeusz).setAvailable(false));
        booksCopiesList.add(new BookCopy(panTadeusz));
        booksCopiesList.add(new BookCopy(panTadeusz));
        booksCopiesList.add(new BookCopy(dziady));
        booksCopiesList.add(new BookCopy(dziady).setAvailable(false));
        booksCopiesList.add(new BookCopy(dziady).setAvailable(false));
        booksCopiesList.add(new BookCopy(sonetyKrymskie));

        Author dg = new Author("Dimitry", "Glukhovsky");

        BookDefinition futuRE = new BookDefinition("FUTU.RE", dg);

        for (int i = 0; i < 10; i++) {
            booksCopiesList.add(new BookCopy(futuRE));
        }
        return booksCopiesList;
    }

    /**
     * Counts book copies
     *
     * @param bookDefinitionToCount Book definition that copies is to be counted. If null, all books will by counted.
     * @param availability          Select witch books to count. All of them, onlu borrowed or only available on shelf.
     * @return Count of required books.
     */
    @Override
    @Transactional
    public int countBookCopies(BookDefinition bookDefinitionToCount, BookCopyAvailibility availability) {
        String queryString = "";
        Query query = null;
        if (Objects.isNull(bookDefinitionToCount)) {
            switch (availability) {
                case ALL:
                    queryString = "SELECT COUNT(1) FROM BookCopy";
                    break;
                case IS_AVAILABLE:
                    queryString = "SELECT COUNT(1) FROM BookCopy WHERE isAvailable=true";
                    break;
                case NOT_AVAILABLE_BORROWED:
                    queryString = "SELECT COUNT(1) FROM BookCopy WHERE isAvailable=false";
                    break;
            }
            query = em.createQuery(queryString);
        } else {
            //count specific book
            switch (availability) {
                case ALL:
                    queryString = "SELECT COUNT(1) FROM BookCopy WHERE bookDefinition=:bookDataToCount";
                    break;
                case IS_AVAILABLE:
                    queryString = "SELECT COUNT(1) FROM BookCopy WHERE bookDefinition=:bookDataToCount AND isAvailable=true";
                    break;
                case NOT_AVAILABLE_BORROWED:
                    queryString = "SELECT COUNT(1) FROM BookCopy WHERE bookDefinition=:bookDataToCount AND isAvailable=false";
                    break;
            }
            query = em.createQuery(queryString).setParameter("bookDataToCount", bookDefinitionToCount);
        }

        int singleResult = ((Long)query.getSingleResult()).intValue();
        return singleResult;
    }

    @Override
    @Transactional
    public void addBookCopy(BookCopy bookCopyToAdd){
        em.persist(bookCopyToAdd);
    }

    @Override
    @Transactional
    public void addBookDefinition(BookDefinition bookDefinitionToAdd){
        em.persist(bookDefinitionToAdd);
    }

    @Override
    @Transactional
    public BookCopy getBookCopyById(Integer id){
        BookCopy bookCopy = em.createQuery("FROM BookCopy WHERE id=:id", BookCopy.class).setParameter("id", Long.valueOf(id)).getSingleResult();
        return bookCopy;
    }

    @Override
    @Transactional
    public BookDefinition getBookDefinitionById(Integer id){
        BookDefinition bookDefinition = em.createQuery("FROM BookDefinition WHERE id=:id", BookDefinition.class).setParameter("id",Long.valueOf(id)).getSingleResult();
        return  bookDefinition;
    }

    @Override
    @Transactional
    public int countBookDefinitions() {
        return em.createQuery("SELECT COUNT(1) FROM BookDefinition", Long.class).getSingleResult().intValue();
    }

    @Override
    @Transactional
    public void saveAllBooksDefinitions(List<BookDefinition> bookDefinitions) {
        bookDefinitions.forEach(bookDef->em.persist(bookDef));
    }

    @Override
    @Transactional
    public void saveAllBookCopies(List<BookCopy> bookCopies) {
        bookCopies.forEach(bookCopy -> em.merge(bookCopy));
    }
}
