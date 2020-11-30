package main;

import entity.Book;
import xml.XMLDOM;
import xml.XMLSax;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try (InputStream input = new FileInputStream(new File("obras.xml"));) {
            SAXParser parser = factory.newSAXParser();
            XMLSax reader = new XMLSax();
            parser.parse(input, reader);
            List<Book> works = reader.getBooks();
            works.forEach(System.out::println);
        } catch (ParserConfigurationException | SAXException erro) {
            erro.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        XMLDOM dom = new XMLDOM("obras.xml");
        List<Book> books = dom.readXML();
        books.forEach(System.out::println);
    }
}
