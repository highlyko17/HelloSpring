package hello.hellospring.repository;

import hello.hellospring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    List<Book> findAll();
}
