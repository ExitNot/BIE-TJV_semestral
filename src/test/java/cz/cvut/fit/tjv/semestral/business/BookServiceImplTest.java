package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.BookRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl bookService;
    @MockBean
    private BookRepository bookRepository;

    private final Book book1 = new Book( "Simpsons Comics", "May 2004", 94,
            "24/7th Heaven", null);

//    @Test
//    void rateUp() {
//        Optional<Book> optional_book1 = Optional.of(book1);
//
//        BDDMockito.given(bookRepository.findById(book1.getId())).willReturn(optional_book1);
//        doNothing().when(bookRepository).deleteById(book1.getId());
//        BDDMockito.given(bookRepository.save(book1)).willReturn(book1);
//
//        bookService.RateUp(book1.getId());
//        Assertions.assertEquals((int) book1.getSatisfactionScore(), 1);
//    }

//    @Test
//    void rateDown() {
//    }

//    @Test
//    void create() {
//        Example<Book> book_ex = Example.of(book1);
//
//        BDDMockito.given(bookRepository.findAll(book_ex)).willReturn(Collections.emptyList());
//        BDDMockito.given(bookRepository.save(book1)).willReturn(book1);
//
//        Book result = bookService.create(book1);
//        Assertions.assertEquals(book1, result);
//    }

//    @Test
//    void readById() {
//
//    }
//
//    @Test
//    void readAll() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
}