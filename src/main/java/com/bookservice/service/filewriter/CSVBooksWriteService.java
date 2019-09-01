package com.bookservice.service.filewriter;

import com.bookservice.model.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yurii on 8/12/2019.
 * Class for writting book list to csv file
 */
public class CSVBooksWriteService implements WriteService {
    @Override
    public void writeFile(List<Book> outputList, String outputFileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new File(outputFileName), "UTF-8")) {

            String sb = outputList.parallelStream()
                    .map(s -> concatString(s))
                    .collect(Collectors.joining("\n"));
            writer.write(sb.toString());
            System.out.println(outputFileName + " is written!");

        } catch (FileNotFoundException e) {
            System.err.println("Problems with output file! " + e.getMessage());
        }
    }

    String concatString(Book book) {
        String delimeter = ";";
        String concatedString =
                book.getIsbn() + delimeter
                        + book.getName() + delimeter
                        + book.getAuthor().getFirstName() + "\u0020"
                        + book.getAuthor().getSecondName() + delimeter
                        + book.getYearOfPublishing();
        return concatedString;
    }
}
