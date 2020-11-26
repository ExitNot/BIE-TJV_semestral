package cz.cvut.fit.tjv.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Authentication {
    @Id
    private String login;  // PK

    @OneToOne
    private User userName;  // FK
    @Column(name = "password_hash")
    private String passwordHash;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
