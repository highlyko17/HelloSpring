package hello.hellospring.service;

import hello.hellospring.domain.Book;
import hello.hellospring.repository.BookRepository;
import hello.hellospring.repository.MemoryBookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookRepository bookRepository = new MemoryBookRepository();

    public Long join(Book book){
        validateDuplicateBook(book); // 중복 책 확인
        bookRepository.save(book);
        return book.getId();
    }

    private void validateDuplicateBook(Book book) {
        bookRepository.findByName(book.getName())
            .ifPresent(b -> {
                throw new IllegalStateException("이미 추가된 책입니다.");
            });
    }

    public List<Book> findBoos(){
        return bookRepository.findAll();
    }

    public Optional<Book> findOne(Long bookId){
        return bookRepository.findById(bookId);
    }
}
