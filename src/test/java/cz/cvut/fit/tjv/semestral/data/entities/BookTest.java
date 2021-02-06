package cz.cvut.fit.tjv.semestral.data.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getCreatorId() {
        Book b1 = new Book();
        User u1 = new User();
        User u2 = new User();
        Collection<User> c = new ArrayList<User>();
        c.add(u1);
        c.add(u2);
        b1.setCreatorId(c);
        assertEquals( c, b1.getCreatorId() );
    }

    @Test
    void setCreatorId() {
        Book b1 = new Book();
        User u1 = new User();
        User u2 = new User();
        Collection<User> c = new ArrayList<User>();
        c.add(u1);
        c.add(u2);
        b1.setCreatorId(c);
        assertEquals( c, b1.getCreatorId() );
    }

    @Test
    void getBookName() {
        Book b1 = new Book();
        b1.setBookName("Berserk");
        assertEquals("Berserk", b1.getBookName());
    }

    @Test
    void setBookName() {
        Book b1 = new Book();
        b1.setBookName("Berserk");
        assertEquals("Berserk", b1.getBookName());
    }

    @Test
    void getWrittenBy() {
        Book b1 = new Book();
        b1.setBookName("Berserk");
        assertEquals("Berserk", b1.getBookName());
    }

    @Test
    void getPublishDate() {
        Book b1 = new Book();
        b1.setPublishDate("1989");
        assertEquals("1989", b1.getPublishDate());
    }

    @Test
    void setPublishDate() {
        Book b1 = new Book();
        b1.setPublishDate("1989");
        assertEquals("1989", b1.getPublishDate());
    }

    @Test
    void getDescription() {
        Book b1 = new Book();
        b1.setDescription("His name is Guts, the Black Swordsman, a feared warrior spoken of only in whispers. Bearer of a gigantic sword, an iron hand, and the scars of countless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol that draws the forces of darkness to him and dooms him as their sacrifice. But Guts won't take his fate lying down; he'll cut a crimson swath of carnage through the ranks of the damned - and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Guts relentlessly follows a dark, bloodstained path that leads only to death...or vengeance.");
        assertEquals("His name is Guts, the Black Swordsman, a feared warrior spoken of only in whispers. Bearer of a gigantic sword, an iron hand, and the scars of countless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol that draws the forces of darkness to him and dooms him as their sacrifice. But Guts won't take his fate lying down; he'll cut a crimson swath of carnage through the ranks of the damned - and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Guts relentlessly follows a dark, bloodstained path that leads only to death...or vengeance.",
                b1.getDescription());
    }

    @Test
    void setDescription() {
        Book b1 = new Book();
        b1.setDescription("His name is Guts, the Black Swordsman, a feared warrior spoken of only in whispers. Bearer of a gigantic sword, an iron hand, and the scars of countless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol that draws the forces of darkness to him and dooms him as their sacrifice. But Guts won't take his fate lying down; he'll cut a crimson swath of carnage through the ranks of the damned - and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Guts relentlessly follows a dark, bloodstained path that leads only to death...or vengeance.");
        assertEquals("His name is Guts, the Black Swordsman, a feared warrior spoken of only in whispers. Bearer of a gigantic sword, an iron hand, and the scars of countless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol that draws the forces of darkness to him and dooms him as their sacrifice. But Guts won't take his fate lying down; he'll cut a crimson swath of carnage through the ranks of the damned - and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Guts relentlessly follows a dark, bloodstained path that leads only to death...or vengeance.",
                b1.getDescription());
    }

    @Test
    void getIssueNumber() {
        Book b1 = new Book();
        b1.setIssueNumber(1);
        assertEquals(1, (int) b1.getIssueNumber());
    }

    @Test
    void setIssueNumber() {
        Book b1 = new Book();
        b1.setIssueNumber(1);
        assertEquals(1, (int) b1.getIssueNumber());
    }

}