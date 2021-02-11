package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.data.entities.Auth;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.rest_api.model.AuthDto;
import cz.cvut.fit.tjv.semestral.rest_api.model.BookDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthDtoAssembler extends RepresentationModelAssemblerSupport<Auth, AuthDto> {

    public AuthDtoAssembler() {
        super(AuthController.class, AuthDto.class);
    }

    @Override
    public AuthDto toModel(Auth entity) {
        if ( entity == null )
            return null;
        return new AuthDto(
                entity.getLogin(),
                entity.getUserName(),
                entity.getPasswordHash()
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).readOne(entity.getLogin())).withSelfRel());
    }
}
