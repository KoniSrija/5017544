package com.library.LibraryManagement7;

public class BookService {
 private BookRepository bookRepository;

 // Constructor for constructor injection
 public BookService(BookRepository bookRepository) {
     this.bookRepository = bookRepository;
 }

 // Setter method for setter injection
 public void setBookRepository(BookRepository bookRepository) {
     this.bookRepository = bookRepository;
 }

  public void save(String book)
  {
	  bookRepository.manage(book);
	  System.out.println(book+"is saved");
  }
}
