package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Test Book");
        testBook.setAuthor("Test Author");
        testBook.setPrice(19.99);
        testBook.setIsbn(1L);
        testBook.setCreatedAt(LocalDateTime.now());
        testBook.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {

        List<Book> expectedBooks = Arrays.asList(testBook);
        when(bookService.getAllBooks()).thenReturn(expectedBooks);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBooks.size(), response.getBody().size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById_ShouldReturnBook_WhenBookExists() {

        when(bookService.getBookById(1L)).thenReturn(testBook);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void createBook_ShouldReturnCreatedBook() {

        when(bookService.createBook((any(Book.class)))).thenReturn(testBook);

        ResponseEntity<Book> response = bookController.createBook(testBook);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).createBook(testBook);

    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() {

        when(bookService.updateBook(anyLong(), any(Book.class))).thenReturn(testBook);
        ResponseEntity<Book> response = bookController.updateBook(1L, testBook);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).updateBook(1L, testBook);

    }

    @Test
    void deleteBook_ShouldReturnNoContent() {

        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    void getBookByIsbn_ShouldReturnBook_WhenBookExists() {

        when(bookService.getBookByIsbn(1L)).thenReturn(testBook);

        ResponseEntity<Book> response = bookController.getBookByIsbn(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).getBookByIsbn(1L);
    }

    @Test
    void getBookByName_ShouldReturnBook_WhenBookExists() {

        when(bookService.getBookByName("Test Book")).thenReturn(testBook);

        ResponseEntity<Book> response = bookController.getBookByName("Test Book");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).getBookByName("Test Book");
    }

    @Test
    void updateBookStocks_ShouldReturnUpdatedBook () {
        when(bookService.updateBookStocks(1L)).thenReturn(testBook);

        ResponseEntity<Book> response = bookController.updateBookStocks(1L, testBook);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testBook.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).updateBookStocks(1L);
    }
}
