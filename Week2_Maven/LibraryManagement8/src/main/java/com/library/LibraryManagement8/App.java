package com.library.LibraryManagement8;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.service.BookService;

public class App {
 public static void main(String[] args) {
     // Load the Spring context from the XML configuration file
     ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

     // Retrieve the BookService bean from the context
     BookService bookService = (BookService) context.getBean("bookService");

     // Call a method on bookService to test the aspect
     bookService.save("The Little star book");

     System.out.println("BookService and BookRepository have been configured and loaded successfully.");
 }
}
