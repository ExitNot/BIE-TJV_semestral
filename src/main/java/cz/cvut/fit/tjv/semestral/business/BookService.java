package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.entities.*;
import org.springframework.web.multipart.MultipartFile;

public interface BookService extends CrudService<Book, String>{
    void RateUp(Book b) throws IllegalAccessException;
    void RateDown(Book b) throws IllegalAccessException;
//    Book create(Book b, MultipartFile img);
}
