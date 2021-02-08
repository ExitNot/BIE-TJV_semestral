package cz.cvut.fit.tjv.semestral.business;

import cz.cvut.fit.tjv.semestral.data.BookRepository;
import cz.cvut.fit.tjv.semestral.data.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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
    public Book create(Book data, MultipartFile img) {
        try {
            data.setImg(Base64.getEncoder().encodeToString(img.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Example<Book> example = Example.of(data);

        if( !bookRepository.findAll(example).isEmpty() ){
            throw new ExistingEntityException();
        }
        return bookRepository.save(data);
    }

    @Override
    public Book create(Book data) {
//        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<Book> example = Example.of(data);

        if( !bookRepository.findAll(example).isEmpty() ){
            throw new ExistingEntityException();
        }
        return bookRepository.save(data);
    }

    @Override
    public Optional<Book> readById(String id) {
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
    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}
