package com.example.bookstore.feign;

import com.example.bookstore.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "STUDENTDETAIL")
public interface StudentClient {


    @GetMapping("/asarfi/students/by-roll")
    String getStudentByRoll(@RequestParam("roll") Long roll);


    @GetMapping("/asarfi/students")
    List<String> getAllStudents();
}
