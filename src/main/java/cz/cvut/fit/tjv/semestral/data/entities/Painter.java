package cz.cvut.fit.tjv.semestral.data.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Painter {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId; //  FK

    @ManyToMany
    @JoinTable(
            name = "books_arted_by",
            joinColumns = @JoinColumn(name = "art_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Collection<Book> booksArtedBy;

    private String pseudonym;
    private String info;


}
