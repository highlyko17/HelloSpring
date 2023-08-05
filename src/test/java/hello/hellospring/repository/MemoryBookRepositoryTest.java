package hello.hellospring.repository;

import hello.hellospring.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.PresentationDirection;
import java.util.List;
import java.util.Optional;

public class MemoryBookRepositoryTest {

    MemoryBookRepository repository = new MemoryBookRepository();

    // 각 메소드의 실행이 끝날 때마다 동작
    // 클래스 전체를 테스트하면 메소드의 순서는 보장이 안 되어서 전 메소드에서 테스트할 때 썼던 값이 저장 되어 있울 수 있다.
    // 그래서 각 메소드 끝나고 정리를 해야 함
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Book book = new Book();
        book.setName("book one");

        repository.save(book);

        Book result = repository.findById(book.getId()).get();
        // System.out.println("result = " + (result == book));
        //Assertions.assertEquals(book, result);
        Assertions.assertThat(book).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Book book1 = new Book();
        book1.setName("book1");
        repository.save(book1);

        Book book2 = new Book();
        book2.setName("book2");
        repository.save(book2);

        Book result = repository.findByName("book2").get();

        Assertions.assertThat(result).isEqualTo(book2);
    }

    @Test
    public void findAll(){
        Book book1 = new Book();
        book1.setName("book1");
        repository.save(book1);

        Book book2 = new Book();
        book2.setName("book2");
        repository.save(book2);

        List<Book> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
