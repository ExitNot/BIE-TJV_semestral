package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import cz.cvut.fit.tjv.semestral.rest_api.model.UserDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserDtoAssembler extends RepresentationModelAssemblerSupport<User, UserDto> {

    public UserDtoAssembler() {
        super(UserController.class, UserDto.class);
    }

    @Override
    public UserDto toModel(@NotNull User entity) {
        if ( entity == null )
            return null;

        return new UserDto(
                entity.getId(),
                entity.getLogin(),
                entity.getUserType(),
                entity.getDisplayedName(),
                entity.getEmail(),
                entity.getBooksCreatedBy() == null ? null : entity.getBooksCreatedBy().stream()
                                .map(i -> i.getId()).collect(Collectors.toCollection(ArrayList::new))
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).readOne(entity.getId())).withSelfRel());
    }
}
