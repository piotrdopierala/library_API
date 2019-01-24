package pl.dopierala.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


public class BorrowingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //private BookCopy borrowedBook; //this class is composed in BookCopy class
    private LibraryUser borrowingPerson;
    private LocalDateTime borrowedTimestamp;
    private LocalDateTime returnedTimestamp;

    public BorrowingHistory() {
    }

    public LibraryUser getBorrowingPerson() {
        return borrowingPerson;
    }

    public void setBorrowingPerson(LibraryUser borrowingPerson) {
        this.borrowingPerson = borrowingPerson;
    }

    public LocalDateTime getBorrowedTimestamp() {
        return borrowedTimestamp;
    }

    public void setBorrowedTimestamp(LocalDateTime borrowedTimestamp) {
        this.borrowedTimestamp = borrowedTimestamp;
    }

    public LocalDateTime getReturnedTimestamp() {
        return returnedTimestamp;
    }

    public void setReturnedTimestamp(LocalDateTime returnedTimestamp) {
        this.returnedTimestamp = returnedTimestamp;
    }
}
