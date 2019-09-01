package com.bookservice.service.bookseparator;

import com.bookservice.model.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yurii on 8/12/2019.
 * Class for filtering book list for new books
 */
public class NewBookBookYearSeparator implements BookYearSeparator {
    public List<Book> separateData(List<Book> inputList, int year) {

        List<Book> outputList = inputList.parallelStream()
                .filter(s -> year <= s.getYearOfPublishing())
                .collect(Collectors.toList());
        return outputList;
    }
}
