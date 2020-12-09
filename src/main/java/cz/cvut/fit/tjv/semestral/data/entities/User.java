package cz.cvut.fit.tjv.semestral.data.entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;  // PK

    @OneToOne
    private Authentication login;  // FK

    private String displayedName;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
