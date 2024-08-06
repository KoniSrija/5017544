package com.library.LibraryManagement6;
import org.springframework.stereotype.Repository;
@Repository
public class BookRepository {
   public void manage(String book)
   {
	   System.out.println(book+" is reposited");
   }
}
