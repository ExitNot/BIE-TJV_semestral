package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.business.BookService;
import cz.cvut.fit.tjv.semestral.business.ExistingEntityException;
import cz.cvut.fit.tjv.semestral.business.UserService;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import cz.cvut.fit.tjv.semestral.rest_api.model.BookDto;
import cz.cvut.fit.tjv.semestral.rest_api.model.UserDto;
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

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping( path = "/api/v1/users" )
public class UserController {
    private final BookService bookService;
    private final UserService userService;
    private final UserDtoAssembler userDtoAssembler;
    private final PagedResourcesAssembler<User> pagedResourcesAssembler;

    @Autowired
    public UserController(BookService bookService, UserService userService,
                          UserDtoAssembler userDtoAssembler, PagedResourcesAssembler<User> pagedResourcesAssembler){
        this.bookService = bookService;
        this.userService = userService;
        this.userDtoAssembler = userDtoAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    private User toEntity( UserDto dto ){
        if ( dto == null )
            return null;
        return new User(
                dto.getLogin(),
                dto.getDisplayedName(),
                dto.getUserType(),
                dto.getEmail(),
                dto.getBooksCreatedBy() == null ? null :
                        dto.getBooksCreatedBy().stream()
                                .map(i -> bookService.readById(i)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT)))
                                .collect(Collectors.toCollection(ArrayList::new))
        );
    }


    @PostMapping
    public void create(@RequestBody UserDto data){
        try {
            userService.create(toEntity(data));
        } catch (ExistingEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/id={id}")  // "/?id={id}" is not working
    public UserDto readOne(@PathVariable("id") Long id){
        return userDtoAssembler.toModel(
                userService.readById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).readAll(0, 5)
        ).withRel(IanaLinkRelations.COLLECTION));
    }

    @GetMapping
    public PagedModel<UserDto> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return pagedResourcesAssembler.toModel(userService.readAll(PageRequest.of(page, size)), userDtoAssembler);
    }

    @PutMapping("/id={id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody UserDto data)
    {
        userService.update(id, toEntity(data));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id){
        try{
            userService.delete(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
