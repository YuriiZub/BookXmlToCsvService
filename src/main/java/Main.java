import com.bookservice.err.DataEmptyException;
import com.bookservice.service.BookYearXmlToCsvSeparatorService;
import com.bookservice.service.YearSeparatorService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String args[])  {

        int year = 2015;
        String inputFileName = "knihy.xml";
        String oldBooksFilename = "knihy_stare.csv";
        String newBooksFilename = "knihy_nove.csv";

        YearSeparatorService separatorService = new BookYearXmlToCsvSeparatorService();

        try {
            separatorService.separateAndSave(year, inputFileName, oldBooksFilename, newBooksFilename);
        }
        catch (IOException  e){
            System.err.println("IO exception!" + e.getMessage());
        }
        catch (SAXException  e){
            System.err.println("Syntax XMl file exception!" + e.getMessage());
        }
        catch (ParserConfigurationException  e){
            System.err.println("Parser configuration Exception!" + e.getMessage());
        }
        catch(DataEmptyException e){
            System.err.println( e.getMessage());
        }
        catch (Exception  e){
            System.err.println("Other Exceptions!" + e.getMessage());
        }
    }
}
