package pl.dopierala.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class BookDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<Author> authors;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private BookGenere genere;
    @OneToMany(mappedBy = "bookData", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Collection<BookCopy> copies;

    public BookDefinition(String title, Author... author) {
        if (Objects.nonNull(title)) {
            this.title = title;
        }
        this.authors = new ArrayList<>();
        if (author.length > 0) {
            this.authors.addAll(Arrays.asList(author));
        }
    }

    public BookDefinition() {
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setGenere(BookGenere genere) {
        this.genere = genere;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    public void addCopy(BookCopy copy) {
        if(Objects.isNull(this.copies)){
            this.copies=new ArrayList<>();
        }
        this.copies.add(copy);
    }
}

