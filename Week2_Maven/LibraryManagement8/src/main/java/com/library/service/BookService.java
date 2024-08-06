package com.library.service;

import com.library.LibraryManagement8.BookRepository;

public class BookService {
	  private BookRepository bookrepository;
	  public void setBookRepository(BookRepository bookrepository)
	  {
		  this.bookrepository=bookrepository;
	  }
	  public void save(String book) {
	      bookrepository.manage(book);
	      System.out.println( book+" is saved");
	  }
	}