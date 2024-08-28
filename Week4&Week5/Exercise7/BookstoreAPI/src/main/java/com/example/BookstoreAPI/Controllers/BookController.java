package com.example.BookstoreAPI.Controllers;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.mapper.BookMapper;
import com.example.BookstoreAPI.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookRepository.findAll().stream()
                .map(BookMapper.INSTANCE::bookToBookDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .header("Custom-Header", "AllBooksRetrieved")
                .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book.get());
            return ResponseEntity.ok()
                    .header("Custom-Header", "BookFound")
                    .body(bookDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Error-Header", "BookNotFound")
                    .build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        BookDTO savedBookDTO = BookMapper.INSTANCE.bookToBookDTO(savedBook);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Custom-Header", "BookCreated")
                .body(savedBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setPrice(bookDTO.getPrice());
            book.setIsbn(bookDTO.getIsbn());
            bookRepository.save(book);
            BookDTO updatedBookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
            return ResponseEntity.ok()
                    .header("Custom-Header", "BookUpdated")
                    .body(updatedBookDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Error-Header", "BookNotFound")
                    .build();
        }
    }

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
