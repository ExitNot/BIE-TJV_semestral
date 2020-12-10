package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.entities.*;

public interface BookService extends CrudService<Book, Long>{
    void RateUp(Book b) throws IllegalAccessException;
    void RateDown(Book b) throws IllegalAccessException;
}
