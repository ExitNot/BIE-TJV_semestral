package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.business.BookService;
import cz.cvut.fit.tjv.semestral.business.ExistingEntityException;
import cz.cvut.fit.tjv.semestral.business.UserService;
import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import cz.cvut.fit.tjv.semestral.rest_api.model.BookDto;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    private final Book book1 = new Book( "Simpsons Comics", "May 2004", 94,
            "24/7th Heaven", null);
    private final Book book2 = new Book( "Simpsons Comics", "June 2004", 95,
            "Coach Me If You Can", null);

    @Test
    void create() throws Exception {
        BDDMockito.given(bookService.create(book1)).willReturn(book1);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/books")
                        .content("{\"bookName\" : \"Simpsons Comics\", \"issueNumber\" : 94, " +
                                "\"publishDate\" : \"May 2004\", \"description\" : \"24/7th Heaven\", " +
                                "\"creatorId\" : [] }")
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createConflict() throws Exception {
        BDDMockito.given(bookService.create(book2)).willThrow(ExistingEntityException.class);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/books")
                    .content("{\"bookName\" : \"Simpsons Comics\", \"issueNumber\" : 95, " +
                            "\"publishDate\" : \"June 2004\", \"description\" : \"Coach Me If You Can\"}")
                    .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    void readOne() throws Exception {
        Optional<Book> returnBookValue = Optional.of(book1);

        BDDMockito.given(bookService.readById(book1.getId())).willReturn(returnBookValue);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books/id={id}", book1.getId())
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href", CoreMatchers.endsWith("/api/v1/books/id=SimpsonsComics94")));
    }


    @Test
    void readAll() throws Exception {
        final List<Book> data = List.of(book1, book2);
        final Pageable pageable = PageRequest.of( 0, 2);
        final Page<Book> pageExpected = new PageImpl<>(data, pageable, 3);

        BDDMockito.given(bookService.readAll(pageable)).willReturn(pageExpected);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books?page={page}&size={size}", 0, 2)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href", CoreMatchers.endsWith("/api/v1/books?page=0&size=2")));
    }

//    @Test
    void update() {
//        Book old_book = book1;
//        old_book.setDescription("---------");
//
//        doNothing().when(bookService).update(old_book.getId() ,book1);  // bookService
//        BDDMockito.given(bookService.readById(old_book.getId())).willReturn(Optional.of(book1));
//
////        bookService.update(old_book.getId(), book1);
////        old_book = bookService.readById(old_book.getId()).get();
////        Assertions.assertEquals(book1, old_book);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/api/v1/books/id={id}", old_book.getId())
//                        .content("{\"bookName\" : \"Simpsons Comics\", \"issueNumber\" : 94, " +
//                        "\"publishDate\" : \"May 2004\", \"description\" : \"24/7th Heaven\" }")
//                        .contentType("application/json")
//        ).;
    }

//    @Test
    void delete() {

    }
}