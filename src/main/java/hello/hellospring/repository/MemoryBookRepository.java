package hello.hellospring.repository;

import hello.hellospring.domain.Book;

import java.util.*;

public class MemoryBookRepository implements BookRepository{

    private static Map<Long, Book> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        store.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Book> findByName(String name) {
        return store.values().stream()
                .filter(book -> book.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
