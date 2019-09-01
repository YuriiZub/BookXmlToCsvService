package com.bookservice.service;

import com.bookservice.err.DataEmptyException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Yurii on 8/12/2019.
 */
public interface YearSeparatorService {

    void separateAndSave(int year, String inputFile, String oldYearsOutputFile, String newYearsOutputFile) throws IOException, ParserConfigurationException, SAXException, DataEmptyException;
}
