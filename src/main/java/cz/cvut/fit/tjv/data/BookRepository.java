package cz.cvut.fit.tjv.data;

import cz.cvut.fit.tjv.data.entities.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
