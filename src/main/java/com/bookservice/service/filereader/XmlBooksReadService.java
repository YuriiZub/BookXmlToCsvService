package com.bookservice.service.filereader;

import com.bookservice.err.DataEmptyException;
import com.bookservice.model.Author;
import com.bookservice.model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 8/12/2019.
 * Class for reading book list from xml
 */

public class XmlBooksReadService implements ReadService {

    public List<Book> readFile(String fileName) throws ParserConfigurationException, SAXException, IOException, DataEmptyException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fileName));

        NodeList bookElements = document.getDocumentElement().getElementsByTagName("Kniha");

        int totalElementsCount = bookElements.getLength();

        ArrayList<Book> books = new ArrayList(totalElementsCount);

        if (bookElements.getLength() == 0)
            throw new DataEmptyException("Relevant Data not found in XML file:" + fileName);

        int relevantStringCounter = 0;

        for (int i = 0; i < bookElements.getLength(); i++) {
            Node book = bookElements.item(i);
            NamedNodeMap attributes = book.getAttributes();
            Element elementBook = (Element) book;

            Book bookFromXml = this.makeBookFromXML(attributes, elementBook, i);
            if (bookFromXml != null) {
                books.add(bookFromXml);
                relevantStringCounter++;
            }
        }

        if (relevantStringCounter == 0)
            throw new DataEmptyException("All data with the problems in XML file:" + fileName);
        return books;
    }

    Book makeBookFromXML(NamedNodeMap attributes, Element elementBook, int i) {
        try {
            String isbn = attributes.getNamedItem("ISBN").getNodeValue();
            int yearOfPublishing = Integer.parseInt(attributes.getNamedItem("Vydano").getNodeValue());
            String bookName = elementBook.getElementsByTagName("Nazev").item(0).getTextContent();
            Node nodeAuthor = elementBook.getElementsByTagName("Autor").item(0);
            String authorFirstName = nodeAuthor.getAttributes().getNamedItem("Jmeno").getNodeValue();
            String authorSecondName = nodeAuthor.getAttributes().getNamedItem("Prijmeni").getNodeValue();
            Author author = new Author(authorFirstName, authorSecondName);

            return new Book(
                    isbn,
                    yearOfPublishing,
                    bookName,
                    author
            );

        } catch (NullPointerException e) {
            System.err.println("Error of reading XML in object number:" + i + " Object is lost!!");
            return null;
        }
    }
}
