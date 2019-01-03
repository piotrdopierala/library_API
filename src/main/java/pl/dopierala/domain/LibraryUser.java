package pl.dopierala.domain;

import javax.persistence.*;

@Entity
public class LibraryUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

}
