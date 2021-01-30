package cz.cvut.fit.tjv.semestral.data.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getLogin() {
        User u1 = new User();
        Auth auth1 = new Auth();
        auth1.setLogin("login");
        u1.setLogin(auth1);
        assertEquals(auth1, u1.getLogin());
    }

    @Test
    void setLogin() {
        User u1 = new User();
        Auth auth1 = new Auth();
        auth1.setLogin("login");
        u1.setLogin(auth1);
        assertEquals(auth1, u1.getLogin());
    }

    @Test
    void getDisplayedName() {
        User u1 = new User();
        u1.setDisplayedName("Name");
        assertEquals("Name", u1.getDisplayedName());
    }

    @Test
    void setDisplayedName() {
        User u1 = new User();
        u1.setDisplayedName("Name");
        assertEquals("Name", u1.getDisplayedName());
    }

    @Test
    void getEmail() {
        User u1 = new User();
        u1.setEmail("name@outlook.com");
        assertEquals("name@outlook.com", u1.getEmail());
    }

    @Test
    void setEmail() {
        User u1 = new User();
        u1.setEmail("name@outlook.com");
        assertEquals("name@outlook.com", u1.getEmail());
    }
}