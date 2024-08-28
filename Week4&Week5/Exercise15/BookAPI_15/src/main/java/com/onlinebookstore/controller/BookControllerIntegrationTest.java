package com.onlinebookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebookstore.entity.Book;
import com.onlinebookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book(null, "Integration Title", "Integration Author", 29.99);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Integration Title"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book(null, "Integration Title", "Integration Author", 29.99);
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/" + savedBook.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Integration Title"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book(null, "Integration Title", "Integration Author", 29.99);
        Book savedBook = bookRepository.save(book);

        Book updatedBook = new Book(savedBook.getId(), "Updated Title", "Updated Author", 39.99);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/" + savedBook.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteBook() throws Exception {
        Book book = new Book(null, "Integration Title", "Integration Author", 29.99);
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/" + savedBook.getId()))
                .andExpect(status().isNoContent());
    }
}
