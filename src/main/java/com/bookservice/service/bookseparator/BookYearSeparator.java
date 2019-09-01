package com.bookservice.service.bookseparator;

import com.bookservice.model.Book;

import java.util.List;

public interface BookYearSeparator {
    List separateData (List<Book> inputList, int year);
}
