package com.library.LibraryManagement6;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void save(String book)
    {
    	bookRepository.manage(book);
    	System.out.println(book+" is saved");
    }
}
