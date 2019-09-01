package com.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String isbn;
    private int yearOfPublishing;
    private String name;
    Author author;
}
