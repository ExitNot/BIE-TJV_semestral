package cz.cvut.fit.tjv.semestral.rest_api.model;

import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Collection;

public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private Auth login;
    private Collection<Book> booksCreatedBy;
    private String UserType;
    private String displayedName;
    private String email;

    UserDto(){}

    public UserDto(Long id, Auth login, Collection<Book> booksCreatedBy, String userType, String displayedName, String email) {
        this.id = id;
        this.login = login;
        this.booksCreatedBy = booksCreatedBy;
        UserType = userType;
        this.displayedName = displayedName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Auth getLogin() {
        return login;
    }

    public void setLogin(Auth login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserDto userDto = (UserDto) o;

        if (!login.equals(userDto.login)) return false;
        if (booksCreatedBy != null ? !booksCreatedBy.equals(userDto.booksCreatedBy) : userDto.booksCreatedBy != null)
            return false;
        if (UserType != null ? !UserType.equals(userDto.UserType) : userDto.UserType != null) return false;
        if (!displayedName.equals(userDto.displayedName)) return false;
        return email.equals(userDto.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + (booksCreatedBy != null ? booksCreatedBy.hashCode() : 0);
        result = 31 * result + (UserType != null ? UserType.hashCode() : 0);
        result = 31 * result + displayedName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
