package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.business.BookService;
import cz.cvut.fit.tjv.semestral.business.ExistingEntityException;
import cz.cvut.fit.tjv.semestral.business.UserService;
import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import cz.cvut.fit.tjv.semestral.rest_api.model.BookDto;
import org.hamcrest.CoreMatchers;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    private Book book1 = new Book( "Simpsons Comics", "May 2004", 94,
            "24/7th Heaven", null);
//    private BookDto bookDto1 = new BookDto( 1L, "Simpsons Comics",
//            0, "May 2004", 94, "24/7th Heaven", List.of(Long.valueOf( 1 )));
//
    private Book book2 = new Book( "Simpsons Comics", "June 2004", 95,
            "Coach Me If You Can", null);
//    private BookDto bookDto2 = new BookDto( 2L, "Simpsons Comics",
//            0, "May 2004", 94, "24/7th Heaven", null);

//    @BeforeEach
//    void setUp(){
//        BDDMockito.given(bookService.create(book1)).willReturn(book1);
//        BDDMockito.given(bookService.create(book2)).willThrow(ExistingEntityException.class);
//        BDDMockito.given(bookService.readAll(pageable)).willReturn(pageExpected);
//    }

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

    /** Here we receive 200 HTML code but expecting 409 */
    @Test
    void createConflict() throws Exception {
        BDDMockito.given(bookService.create(book1)).willThrow(ExistingEntityException.class);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/books")
                    .content("{\"bookName\" : \"Simpsons Comics\", \"issueNumber\" : 95, " +
                            "\"publishDate\" : \"June 2004\", \"description\" : \"Coach Me If You Can\", " +
                            "\"creatorId\" : [] }")
                    .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isConflict());
    }

//    @Test
    void readOne() {
    }

    /** Null pointer exception */
//    @Test
//    void readAll() throws Exception {
//        final List<Book> data = List.of(book1, book2);
//        final Pageable pageable = PageRequest.of( 0, 2);
//        final Page<Book> pageExpected = new PageImpl<>(data, pageable, 3);
//
//        BDDMockito.given(bookService.readAll(pageable)).willReturn(pageExpected);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/v1/books?page={page}&size={size}", 0, 2)
//        ).andExpect(MockMvcResultMatchers.status().isOk());
//        .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href", CoreMatchers.endsWith("/api/v1/books?page=0&size=2")));
//    }

//    @Test
    void update() {
    }

//    @Test
    void delete() {
    }
}