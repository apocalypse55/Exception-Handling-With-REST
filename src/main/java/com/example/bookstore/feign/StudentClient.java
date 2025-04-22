package com.example.bookstore.feign;

import com.example.bookstore.dto.StudentDTO;
import com.example.bookstore.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "STUDENTDETAIL", url = "http://192.168.1.139:8110")
public interface StudentClient {


    @GetMapping("/asarfi/students/by-roll")
    String getStudentByRoll(@RequestParam("roll") Long roll);


    @GetMapping("/asarfi/students")
    List<StudentDTO> getAllStudents();

    @PostMapping("/asarfi/students")
    StudentDTO createStudent(@RequestBody StudentDTO studentDTO);
}
