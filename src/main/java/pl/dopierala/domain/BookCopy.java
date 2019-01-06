package pl.dopierala.domain;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JsonManagedReference
    private BookDefinition bookDefinition;
    private LocalDateTime dateAddedToLibrary;
    private String physicalStateDescription;
    private boolean isAvailable;
    private LocalDateTime borowedDate;
    private LocalDateTime shouldReturnDate;

    public BookCopy(BookDefinition basicData) {
        this.bookDefinition = basicData;
        basicData.addCopy(this);
        this.dateAddedToLibrary=LocalDateTime.now();
        this.isAvailable=true;
    }

    public BookCopy() {
    }

    @Override
    public String toString() {
        return "BookCopy title:"+this.bookDefinition.getTitle()+", by:"+this.bookDefinition.getAuthors();
    }

    public BookDefinition getBookDefinition() {
        return bookDefinition;
    }

    public LocalDateTime getDateAddedToLibrary() {
        return dateAddedToLibrary;
    }

    public String getPhysicalStateDescription() {
        return physicalStateDescription;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDateTime getBorowedDate() {
        return borowedDate;
    }

    public LocalDateTime getShouldReturnDate() {
        return shouldReturnDate;
    }

    public void setPhysicalStateDescription(String physicalStateDescription) {
        this.physicalStateDescription = physicalStateDescription;
    }

    public BookCopy setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    public BookCopy setBorowedDate(LocalDateTime borowedDate) {
        this.borowedDate = borowedDate;
        return this;
    }

    public BookCopy setShouldReturnDate(LocalDateTime shouldReturnDate) {
        this.shouldReturnDate = shouldReturnDate;
        return this;
    }

    public long getId() {
        return id;
    }
}
