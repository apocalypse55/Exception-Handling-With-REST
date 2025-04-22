package com.example.bookstore.controller;

import com.example.bookstore.dto.StudentDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
//import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/search/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable Long isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }
    @GetMapping("/name/{title}")
    public ResponseEntity<Book> getBookByName(@PathVariable String title) {
        return ResponseEntity.ok(bookService.getBookByName(title));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/purchase")
    public ResponseEntity<Book> updateBookStocks(@PathVariable Long id, @RequestBody Book purchaseRequest) {
        return ResponseEntity.ok(bookService.updateBookStocks(id));
    }

    @GetMapping("/student")
    public ResponseEntity<String> getStudentByRoll() {
        String studentDetails = bookService.getStudentByRoll(33L);
        return ResponseEntity.ok(studentDetails);
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = bookService.getAllStudents();
        return students;
    }

    @PostMapping("/students")
    public List<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        List<StudentDTO> students = (List<StudentDTO>) bookService.createStudent(studentDTO);
        return students;
    }
}