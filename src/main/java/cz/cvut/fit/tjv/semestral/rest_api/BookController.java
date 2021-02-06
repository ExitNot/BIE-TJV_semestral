package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.business.BookService;
import cz.cvut.fit.tjv.semestral.business.ExistingEntityException;
import cz.cvut.fit.tjv.semestral.business.UserService;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.rest_api.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping( path = "/api/v1/books" )
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    private final BookDtoAssembler bookDtoAssembler;
    private final PagedResourcesAssembler<Book> pagedResourcesAssembler;

    @Autowired
    public BookController(BookService bookService, UserService userService,
                          BookDtoAssembler bookDtoAssembler, PagedResourcesAssembler<Book> pagedResourcesAssembler){
        this.bookService = bookService;
        this.userService = userService;
        this.bookDtoAssembler = bookDtoAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    private Book toEntity( BookDto dto ){
        if ( dto == null )
            return null;
        return new Book(
                dto.getBookName(),
                dto.getPublishDate(),
                dto.getIssueNumber(),
                dto.getDescription(),
                dto.getCreatorId() == null ? null :
                        dto.getCreatorId().stream()
                            .map(i -> userService.readById(i)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT)))
                            .collect(Collectors.toList())
        );
    }

    @PostMapping
    public void create(@RequestBody BookDto data){
        try {
            bookService.create(toEntity(data));
        } catch (ExistingEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/id={id}")  // "/?id={id}" is not working
    public BookDto readOne(@PathVariable("id") String id){
        return bookDtoAssembler.toModel(
                bookService.readById(Long.parseLong(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).readAll(0, 5)
                                ).withRel(IanaLinkRelations.COLLECTION));
    }

    @GetMapping
    public PagedModel<BookDto> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return pagedResourcesAssembler.toModel(bookService.readAll(PageRequest.of(page, size)), bookDtoAssembler);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BookDto data){
        bookService.update(toEntity(data));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id){
        try{
            bookService.delete(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
