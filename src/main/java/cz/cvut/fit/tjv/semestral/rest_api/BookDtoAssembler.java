package cz.cvut.fit.tjv.semestral.rest_api;

import cz.cvut.fit.tjv.semestral.data.entities.Book;
import cz.cvut.fit.tjv.semestral.data.entities.User;
import cz.cvut.fit.tjv.semestral.rest_api.model.BookDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookDtoAssembler extends RepresentationModelAssemblerSupport<Book, BookDto> {
    public BookDtoAssembler() {
        super(BookController.class, BookDto.class);
    }


    @Override
    public BookDto toModel(Book entity) {
        if ( entity == null )
            return null;
        return new BookDto(
                entity.getId(),
                entity.getBookName(),
                entity.getSatisfactionScore(),
                entity.getPublishDate(),
                entity.getIssueNumber(),
                entity.getDescription(),
                entity.getCreatorId() == null ? null :
                        entity.getCreatorId().stream()
                        .map(i -> i.getId()).collect(Collectors.toList())
        ).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).readOne(entity.getId().toString())).withSelfRel());
    }
}
