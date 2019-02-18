package pl.dopierala.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties("bookDefinition")
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
    @Transient
    List<BorrowingHistory> borrowHistory;

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
        return "BookCopy title:"+this.bookDefinition.getTitle();
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

    public void setPhysicalStateDescription(String physicalStateDescription) {
        this.physicalStateDescription = physicalStateDescription;
    }

    public void setDateAddedToLibrary(LocalDateTime dateAddedToLibrary) {
        this.dateAddedToLibrary = dateAddedToLibrary;
    }

    public BookCopy setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    public long getId() {
        return id;
    }
}
