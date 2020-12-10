package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.BookRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void RateUp(Book b) throws IllegalAccessException {
        Optional<Book> bookOptional = bookRepository.findById(b.getId());

        if( bookOptional.isEmpty() ){
            throw new IllegalAccessException();
        }

        Book book = bookOptional.get();
        book.upSatisfactionScore();
    }

    @Override
    public void RateDown(Book b) throws IllegalAccessException {
        Optional<Book> bookOptional = bookRepository.findById(b.getId());

        if( bookOptional.isEmpty() ){
            throw new IllegalAccessException();
        }

        Book book = bookOptional.get();
        book.downSatisfactionScore();
    }

    @Override
    public Book create(Book data) {
        if( bookRepository.existsById(data.getId()) ){
            throw new ExistingEntityException();
        }
        return bookRepository.save(data);
    }

    @Override
    public Optional<Book> readById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> readAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public void update(Book newData) {
        bookRepository.save(newData);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
