package cz.cvut.fit.tjv.semestral.data.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;  // PK

    @OneToOne
    private Auth login;  // FK

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

    public Auth getLogin() {
        return login;
    }

    public void setLogin(Auth login) {
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
