package pl.dopierala.domain;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private BookDefinition bookData;
    private LocalDateTime dateAddedToLibrary;
    private String physicalStateDescription;
    private boolean isAvailable;
    private LocalDateTime borowedDate;
    private LocalDateTime shouldReturnDate;

    public BookCopy(BookDefinition basicData) {
        this.bookData = basicData;
        basicData.addCopy(this);
        this.dateAddedToLibrary=LocalDateTime.now();
        this.isAvailable=true;
    }

    public BookCopy() {
    }

    @Override
    public String toString() {
        return "BookCopy title:"+this.bookData.getTitle()+", by:"+this.bookData.getAuthors();
    }
}
