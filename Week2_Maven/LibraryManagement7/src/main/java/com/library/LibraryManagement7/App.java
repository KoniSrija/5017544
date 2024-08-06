package com.library.LibraryManagement7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
 public static void main(String[] args) {
     // Load the Spring context from the XML configuration file
     ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

     // Retrieve the BookService bean from the context
     BookService bookService = (BookService) context.getBean("bookService");

     // Use the bookService to test the configuration
     bookService.save("The little kid book");
     System.out.println("BookService and BookRepository have been configured and loaded successfully.");
 }
}

