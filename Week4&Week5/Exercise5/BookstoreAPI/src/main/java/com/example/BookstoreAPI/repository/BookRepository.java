package com.example.BookstoreAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookstoreAPI.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	// Find books by title containing a specific string
    List<Book> findByTitleContaining(String title);

    // Find books by author containing a specific string
    List<Book> findByAuthorContaining(String author);

    // Find books by both title and author containing specific strings
    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);
}



