package com.library.LibraryManagement5;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // Load the Spring context from the XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the BookService bean from the context
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.read("Storybook");

        // Use the bookService to test the configuration
        // For example, call a method on bookService to ensure it's working correctly
        System.out.println("BookService and BookRepository have been configured and loaded successfully.");
    }
}
