package com.example.BookstoreAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookstoreAPI.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}