package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.business.BookService;
import cz.cvut.fit.tjv.semestral.business.ExistingEntityException;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping( path = "/api/v1/books" )
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService ){
        this.bookService = bookService;
    }

    @PostMapping
    public void create(@RequestBody Book data){
        try {
            bookService.create(data);
        } catch (ExistingEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/?id={id}")
    public Book readOne(@RequestParam Long id){
        return bookService.readById(id).get();
    }

    @GetMapping
    public Collection<Book> readAll() {
        return bookService.readAll(Pageable.unpaged()).getContent();
    }

    @PutMapping
    public void update(@RequestBody Book data){
        bookService.update(data);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        bookService.delete(id);
    }
}
