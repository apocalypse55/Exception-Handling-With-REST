package com.example.bookstore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class StudentDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private Long phone_number;
    private String address;
    private LocalDateTime dob;
    private Integer course_id;
}
