package cz.cvut.fit.tjv.semestral.rest_api.model;

import cz.cvut.fit.tjv.semestral.data.entities.User;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class AuthDto extends RepresentationModel<AuthDto> {
    private String login;
    private User userName;
    private String passwordHash;

    AuthDto(){}

    public AuthDto(String login, User userName, String passwordHash) {
        this.login = login;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AuthDto authDto = (AuthDto) o;

        if (!login.equals(authDto.login)) return false;
        if (!userName.equals(authDto.userName)) return false;
        return passwordHash.equals(authDto.passwordHash);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + passwordHash.hashCode();
        return result;
    }
}
