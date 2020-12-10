package cz.cvut.fit.tjv.semestral.data;

        import cz.cvut.fit.tjv.semestral.data.entities.Book;
        import org.springframework.stereotype.Repository;
        import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Book, Long> {
}
