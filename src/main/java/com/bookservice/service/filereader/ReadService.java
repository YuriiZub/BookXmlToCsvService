package com.bookservice.service.filereader;



import com.bookservice.err.DataEmptyException;
import com.bookservice.model.Book;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface ReadService {
    List<Book> readFile(String Filename) throws ParserConfigurationException, SAXException, IOException, DataEmptyException;
}
