package cz.cvut.fit.tjv.semestral.data.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="Users")
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

    public User() {
    }

    public User(Auth login, String displayedName, String email, String userType, Collection<Book> booksCreatedBy) {
        this.login = login;
        this.UserType = userType;
        this.displayedName = displayedName;
        this.email = email;
        this.booksCreatedBy = booksCreatedBy;
    }

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

    public Collection<Book> getBooksCreatedBy() {
        return booksCreatedBy;
    }

    public void setBooksCreatedBy(Collection<Book> booksCreatedBy) {
        this.booksCreatedBy = booksCreatedBy;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
