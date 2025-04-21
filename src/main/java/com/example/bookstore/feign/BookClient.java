package com.example.bookstore.feign;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bookClient", url = "http://192.168.1.151:8082", path = "/api/books")
public interface BookClient {

    @GetMapping("")
    List<Book> getAllBooks();

    @PostMapping()
    Book createBook(@RequestBody Book book);

    @GetMapping ("/name/{title}")
    Book getBookByName(@PathVariable String title);

    @PutMapping("/purchase/{id}")
    Book updateBookStocks(@PathVariable Long id, @RequestBody Book bookDetails);

    @GetMapping("/{id}")
    Book getBookById(@PathVariable Long id);







}
