package com.example.BookstoreAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookstoreAPI.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}