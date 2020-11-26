package cz.cvut.fit.tjv.data.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Author {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId; //  FK

    @ManyToMany
    @JoinTable(
            name = "books_written_by",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Collection<Book> booksWrittenBy;

    private String pseudonym;
    private String info;
}
