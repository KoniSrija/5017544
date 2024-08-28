package com.example.bookapi;

package com.onlinebookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebookstore.entity.Book;
import com.onlinebookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book(1L, "Title", "Author", 29.99);
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
    }

    @Test
    void testGetBookById() throws Exception {
        Book book = new Book(1L, "Title", "Author", 29.99);
        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book = new Book(1L, "Updated Title", "Updated Author", 39.99);
        when(bookService.updateBook(anyLong(), any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }
}


