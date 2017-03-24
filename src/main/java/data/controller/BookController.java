package data.controller;

import data.model.Book;
import data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {
  @Autowired
  private BookRepository _repository;

  @GetMapping("/id/{id}")
  public Book findBookById(@PathVariable("id") long id){
    return _repository.findById(id);
  }

  @GetMapping("/author/{author}")
  public Iterable<Book> findBooksByAuthor(@PathVariable("author") String author){
    return _repository.findByAuthor(author);
  }

  /**
   * Delete all books with the given author
   * @param author provided using path variable
   * @return All books after deletion.
   */
  @DeleteMapping("/author/{author}")
  public Iterable<Book> deleteByAuthor(@PathVariable("author") String author){
    _repository.delete(_repository.findByAuthor(author));
    return _repository.findAll();
  }


  /**
   * Find all books in the database
   * @return all books as a JSON list
   */
  @GetMapping("/book")
  public Iterable<Book> findAllBooks(){
    return _repository.findAll();
  }


  /**
   * Add a new book to the database
   * @param author provided in request parameters
   * @param title provided in request parameters
   * @param year provided in request parameters
   * @return All books including the newly added ones
   */

  @PostMapping("/book")
  public Iterable<Book> addBook(
      @RequestParam("author") String author,
      @RequestParam("title") String title,
      @RequestParam("year") int year
  ){
    _repository.save(new Book(0, title, author, year));
    return _repository.findAll();
  }
}