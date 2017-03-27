package data.repository;

import data.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
  Book findById(long id);
  Iterable<Book> findByAuthor(String author);
  Iterable<Book> findByAuthorAndYear(String author, int year);

  Page<Book> findAll(Pageable pageable);
}

