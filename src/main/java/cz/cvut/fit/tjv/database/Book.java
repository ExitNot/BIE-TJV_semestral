package cz.cvut.fit.tjv.database;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(mappedBy = "writtenBooks")
    private Collection<Author> authorId; // FK

    @Column(name = "book_name")
    private String bookName;
    @Column(name = "written_by")
    private String writtenBy;
    @Column(name = "art_by")
    private String artBy;
    private String genre;
    private String publish_date;
    private String description;
}
