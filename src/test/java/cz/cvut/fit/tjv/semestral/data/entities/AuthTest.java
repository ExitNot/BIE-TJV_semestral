package cz.cvut.fit.tjv.semestral.data.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthTest {

    @Test
    void setLogin() {
        Auth auth = new Auth();
        auth.setLogin("Gregen");

        assertEquals( "Gregen", auth.getLogin() );
    }

    @Test
    void setUserName() {
        Auth auth = new Auth();
        User u1 = new User( auth, "user1", "user1@gmail.com", "Writer", null);
        auth.setUserName(u1);

        assertEquals( u1.getDisplayedName(), auth.getUserName().getDisplayedName() );
    }

    @Test
    void setPasswordHash() {
        Auth auth = new Auth();
        auth.setPasswordHash("r!3ngD#o");

        assertEquals( "r!3ngD#o", auth.getPasswordHash() );
    }
}