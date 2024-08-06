package com.library.LibraryManagement6;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
 public static void main(String[] args) {
    
     ApplicationContext context = new AnnotationConfigApplicationContext(Lconfig.class);

     // Retrieve the BookService bean from the context
     BookService bookService = context.getBean(BookService.class);

     // Use the bookService to test the configuration
    bookService.save("Verity");
     System.out.println("BookService and BookRepository have been configured and loaded successfully.");
 }
}

