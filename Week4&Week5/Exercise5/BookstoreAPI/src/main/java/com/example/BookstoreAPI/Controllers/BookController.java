package com.example.BookstoreAPI.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.repository.BookRepository;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok()
                .header("Custom-Header", "AllBooksRetrieved")
                .body(books);
    }

    // GET a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok()
                    .header("Custom-Header", "BookFound")
                    .body(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Error-Header", "BookNotFound")
                    .build();
        }
    }

    // GET books by title and/or author (using query parameters)
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<Book> books;
        if (title != null && author != null) {
            books = bookRepository.findByTitleContainingAndAuthorContaining(title, author);
        } else if (title != null) {
            books = bookRepository.findByTitleContaining(title);
        } else if (author != null) {
            books = bookRepository.findByAuthorContaining(author);
        } else {
            books = bookRepository.findAll();
        }

        return ResponseEntity.ok()
                .header("Custom-Header", "SearchResults")
                .body(books);
    }

    // POST a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Custom-Header", "BookCreated")
                .body(savedBook);
    }

    // PUT to update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitle(bookDetails.getTitle());
            updatedBook.setAuthor(bookDetails.getAuthor());
            updatedBook.setPrice(bookDetails.getPrice());
            updatedBook.setIsbn(bookDetails.getIsbn());
            bookRepository.save(updatedBook);
            return ResponseEntity.ok()
                    .header("Custom-Header", "BookUpdated")
                    .body(updatedBook);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Error-Header", "BookNotFound")
                    .build();
        }
    }

    // DELETE a book
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent()
                    .header("Custom-Header", "BookDeleted")
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Error-Header", "BookNotFound")
                    .build();
        }
    }
}