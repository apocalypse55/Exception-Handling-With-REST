package com.example.bookstore.service;

import com.example.bookstore.exception.BookAlreadyExistsException;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

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
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.getAllBooks();

        assertEquals(expectedBooks.size(), actualBooks.size());
        assertEquals(expectedBooks.get(0).getId(), actualBooks.get(0).getId());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_ShouldReturnBook_WhenBookExists() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(testBook));

        Book foundBook = bookService.getBookById(1L);

        assertNotNull(foundBook);
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }
    @Test
    void getBookById_WhenBookDoesNotExist_ShouldThrowException() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void createBook_WhenIsbnDoesNotExist_ShouldCreateBook() {

        when(bookRepository.findByIsbn(Long.valueOf(anyString()))).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);


        Book createdBook = bookService.createBook(testBook);


        assertNotNull(createdBook);
        assertEquals(testBook.getTitle(), createdBook.getTitle());
        verify(bookRepository, times(1)).findByIsbn(testBook.getIsbn());
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void createBook_whenIsbnExists_ShouldThrowException() {
        when(bookRepository.findByIsbn(Long.valueOf(anyString()))).thenReturn(Optional.of(testBook));

        assertThrows(BookAlreadyExistsException.class, () -> bookService.createBook(testBook));
        verify(bookRepository, times(1)).findByIsbn(testBook.getIsbn());
        verify(bookRepository, never()).save(testBook);
    }

    @Test
    void updateBook_WhenBookExists_ShouldUpdateBook() {
        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPrice(29.99);
        updatedBook.setIsbn(1L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book result = bookService.updateBook(1L, updatedBook);

        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getAuthor(), result.getAuthor());
        assertEquals(updatedBook.getPrice(), result.getPrice());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(testBook);

    }
    @Test
    void updateBook_WhenBookDoesNotExist_ShouldThrowException() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, testBook));
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void deleteBook_WhenBookExists_ShouldDeleteBook() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(testBook));
        doNothing().when(bookRepository).delete(any(Book.class));


        bookService.deleteBook(1L);


        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(testBook);
    }

    @Test
    void deleteBook_WhenBookDoesNotExist_ShouldThrowException() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).delete(any(Book.class));
    }

    @Test
    void getBookByIsbn_ShouldReturnBook_WhenBookExists() {
        when(bookRepository.findByIsbn(anyLong())).thenReturn(Optional.of(testBook));

        Book foundBook = bookService.getBookByIsbn(1L);

        assertNotNull(foundBook);
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        verify(bookRepository, times(1)).findByIsbn(1L);
    }

    @Test
    void getBookByIsbn_WhenBookDoesNotExist_ShouldThrowException() {

        when(bookRepository.findByIsbn(anyLong())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByIsbn(1L));
        verify(bookRepository, times(1)).findByIsbn(1L);
    }

    @Test
    void getBookByName_ShouldReturnBook_WhenBookExists() {
        when(bookRepository.findByTitle(anyString())).thenReturn(Optional.of(testBook));

        Book foundBook = bookService.getBookByName("Test Book");

        assertNotNull(foundBook);
        assertEquals(testBook.getTitle(), foundBook.getTitle());
        verify(bookRepository, times(1)).findByTitle("Test Book");
    }

    @Test
    void getBookByName_WhenBookDoesNotExist_ShouldThrowException() {

        when(bookRepository.findByTitle(anyString())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByName("Test Book"));
        verify(bookRepository, times(1)).findByTitle("Test Book");
    }

    @Test
    void updateBookStocks_ShouldUpdateBookStock_WhenBookExists() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);
        Book updatedBook = bookService.updateBookStocks(1L);
        assertNotNull(updatedBook);
        assertEquals(testBook.getStock() - 1, updatedBook.getStock());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void updateBookStocks_WhenBookDoesNotExist_ShouldThrowException() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.updateBookStocks(1L));
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
    }
}
