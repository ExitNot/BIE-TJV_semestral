package cz.cvut.fit.tjv.semestral.data.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="\"User\"")
public class User {
    @Id
    @GeneratedValue
    private Long id;  // PK

    @OneToOne
    private Authentication login;  // FK

    @ManyToMany
    @JoinTable(
            name = "books_created_by",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Collection<Book> booksCreatedBy;

    private String UserType;
    private String displayedName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authentication getLogin() {
        return login;
    }

    public void setLogin(Authentication login) {
        this.login = login;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
