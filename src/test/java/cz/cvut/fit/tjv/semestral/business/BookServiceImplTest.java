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
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = {cz.cvut.fit.tjv.semestral.business.BookServiceImpl.class})
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl bookService;
    @MockBean
    private BookRepository bookRepository;

    private final Book book1 = new Book( "Simpsons Comics", "May 2004", 94,
            "24/7th Heaven", null);
    private final Book book2 = new Book( "Simpsons Comics", "June 2004", 95,
            "Coach Me If You Can", null);

    @Test
    void rateUp() {
        Optional<Book> optional_book1 = Optional.of(book1);

        BDDMockito.given(bookRepository.findById(book1.getId())).willReturn(optional_book1);
        doNothing().when(bookRepository).deleteById(book1.getId());
        BDDMockito.given(bookRepository.save(book1)).willReturn(book1);

        bookService.RateUp(book1.getId());
        Assertions.assertEquals((int) book1.getSatisfactionScore(), 1);
    }

    @Test
    void rateDown() {
        Optional<Book> optional_book1 = Optional.of(book1);

        BDDMockito.given(bookRepository.findById(book1.getId())).willReturn(optional_book1);
        doNothing().when(bookRepository).deleteById(book1.getId());
        BDDMockito.given(bookRepository.save(book1)).willReturn(book1);

        bookService.RateDown(book1.getId());
        Assertions.assertEquals((int) book1.getSatisfactionScore(), -1);
    }

    @Test
    void create() {
        Example<Book> book_ex = Example.of(book1);

        BDDMockito.given(bookRepository.findAll(book_ex)).willReturn(Collections.emptyList());
        BDDMockito.given(bookRepository.save(book1)).willReturn(book1);

        Book result = bookService.create(book1);
        Assertions.assertEquals(book1, result);
    }

    @Test
    void readById() {
        Optional<Book> optional_book1 = Optional.of(book1);

        BDDMockito.given(bookRepository.findById(book1.getId())).willReturn(optional_book1);

        Optional<Book> result = bookService.readById(book1.getId());
        Assertions.assertEquals(optional_book1.get(), result.get());
    }

    @Test
    void readAll() {
        final List<Book> data = List.of(book1, book2);
        final Pageable pageable = PageRequest.of( 0, 2);
        final Page<Book> pageExpected = new PageImpl<>(data, pageable, 3);

        BDDMockito.given(bookRepository.findAll(pageable)).willReturn(pageExpected);

        Assertions.assertEquals(pageExpected ,bookService.readAll(pageable));
    }

    @Test
    void update() {
        Book old_book = book1;
        old_book.setDescription("---------");

        doNothing().when(bookRepository).deleteById(old_book.getId());
        BDDMockito.given(bookRepository.save(book2)).willReturn(book2); //bookRepository.findById(id)
        BDDMockito.given(bookRepository.findById(old_book.getId())).willReturn(Optional.of(book1));

        bookService.update(old_book.getId(), book1);
        old_book = bookService.readById(old_book.getId()).get();
        Assertions.assertEquals(book1, old_book);
    }

    @Test
    void delete() {
        doNothing().when(bookRepository).deleteById(book1.getId());
        BDDMockito.given(bookRepository.findById(book1.getId())).willReturn(Optional.empty());

        bookService.delete(book1.getId());
        Optional<Book> res = bookService.readById(book1.getId());
        Assertions.assertEquals(Optional.empty(), res);
    }
}