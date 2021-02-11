package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.business.AuthService;
import cz.cvut.fit.tjv.semestral.business.BookService;
import cz.cvut.fit.tjv.semestral.business.ExistingEntityException;
import cz.cvut.fit.tjv.semestral.business.UserService;
import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.rest_api.model.AuthDto;
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
@RequestMapping( path = "/api/v1/auth" )
public class AuthController {
    private final AuthService authService;
    private final AuthDtoAssembler authDtoAssembler;
    private final PagedResourcesAssembler<Auth> pagedResourcesAssembler;

    @Autowired
    public AuthController(AuthService authService, AuthDtoAssembler authDtoAssembler, PagedResourcesAssembler<Auth> pagedResourcesAssembler){
        this.authService = authService;
        this.authDtoAssembler = authDtoAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    private Auth toEntity( AuthDto dto ){
        if ( dto == null )
            return null;
        return new Auth(
                dto.getLogin(),
                dto.getUserName(),
                dto.getPasswordHash()
                );
    }

    @PostMapping
    public void create(@RequestBody AuthDto data){
        try {
            authService.create(toEntity(data));
        } catch (ExistingEntityException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/id={id}")  // "/?id={id}" is not working
    public AuthDto readOne(@PathVariable("id") String id){
        return authDtoAssembler.toModel(
                authService.readById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).readAll(0, 5)
        ).withRel(IanaLinkRelations.COLLECTION));
    }

    @GetMapping
    public PagedModel<AuthDto> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return pagedResourcesAssembler.toModel(authService.readAll(PageRequest.of(page, size)), authDtoAssembler);
    }

    @PutMapping("/id={id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") String id, @RequestBody AuthDto data)
    {
        authService.update(id, toEntity(data));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String id){
        try{
            authService.delete(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
