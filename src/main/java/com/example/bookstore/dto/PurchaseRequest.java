package com.example.bookstore.dto;

import lombok.Data;

@Data
public class PurchaseRequest {
    private Long bookId;
    private int quantity;
}
