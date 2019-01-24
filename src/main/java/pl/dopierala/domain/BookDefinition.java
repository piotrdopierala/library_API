package pl.dopierala.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class BookDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String isbn;
    private Integer pageCount;
    private LocalDate publishedDate;
    private String thumbnailUrl;
    private String shortDescription;
    private Collection<String> categories;
    @ManyToMany(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Author> authors;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private BookGenere genere;
    @OneToMany(mappedBy = "bookDefinition", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonBackReference
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

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public BookGenere getGenere() {
        return genere;
    }

    public Collection<BookCopy> getCopies() {
        return copies;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Collection<String> getCategories() {
        return categories;
    }

    public void setCategories(Collection<String> categories) {
        this.categories = categories;
    }
}

