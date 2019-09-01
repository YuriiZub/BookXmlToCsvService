package com.bookservice.service;

import com.bookservice.err.DataEmptyException;
import com.bookservice.model.Book;
import com.bookservice.service.bookseparator.BookYearSeparator;
import com.bookservice.service.bookseparator.NewBookBookYearSeparator;
import com.bookservice.service.bookseparator.OldBookBookYearSeparator;
import com.bookservice.service.filereader.ReadService;
import com.bookservice.service.filereader.XmlBooksReadService;
import com.bookservice.service.filewriter.CSVBooksWriteService;
import com.bookservice.service.filewriter.WriteService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yurii on 8/12/2019.
 * Class for main service
 */
public class BookYearXmlToCsvSeparatorService implements YearSeparatorService {
    @Override
    public void separateAndSave(int year, String inputFileName, String oldBooksFilename, String newBooksFilename) throws IOException, ParserConfigurationException, SAXException, DataEmptyException {

        ReadService readService = new XmlBooksReadService();
        List<Book> inputList = readService.readFile(inputFileName);

        //Get list with old books
        BookYearSeparator oldBooksSeparator = new OldBookBookYearSeparator();
        List oldBooksList = oldBooksSeparator.separateData(inputList, year);

        //Get list with new books
        BookYearSeparator newBooksSeparator = new NewBookBookYearSeparator();
        List newBooksList = newBooksSeparator.separateData(inputList, year);

        //Write file with old books
        WriteService oldBookwriteService = new CSVBooksWriteService();
        oldBookwriteService.writeFile(oldBooksList, oldBooksFilename);

        //Write file with new books
        WriteService newBookwriteService = new CSVBooksWriteService();
        newBookwriteService.writeFile(newBooksList, newBooksFilename);

    }
}
