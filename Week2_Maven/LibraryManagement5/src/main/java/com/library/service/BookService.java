package com.library.service;
 import com.library.repository.*;
public class BookService {
  private BookRepository bookrepository;
  public void setBookRepository(BookRepository bookrepository)
  {
	  this.bookrepository=bookrepository;
  }
  public void read(String book) {
      bookrepository.save(book);
      System.out.println("Book is read: " + book);
  }
}
