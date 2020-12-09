package cz.cvut.fit.tjv.semestral;

import cz.cvut.fit.tjv.semestral.data.BookRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DataApplication {
    @Autowired
    private BookRepository bookRepository;

    public static void main( String[] args ) {
        SpringApplication.run( DataApplication.class, args );
    }

    @EventListener( ApplicationReadyEvent.class )
    public void afterStartup() {
        Book b = new Book();
        b.setBookName("Kafka on the shore");
        b.setWrittenBy("Haruki Murakami");
        bookRepository.save(b);
    }
}
