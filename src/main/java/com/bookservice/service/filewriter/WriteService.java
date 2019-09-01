package com.bookservice.service.filewriter;

import com.bookservice.model.Book;

import java.io.IOException;
import java.util.List;

public interface WriteService {
    void writeFile(List<Book> outputList, String outputFileName) throws IOException;
}
