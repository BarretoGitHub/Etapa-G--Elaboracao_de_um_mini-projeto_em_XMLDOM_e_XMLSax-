package xml;

import entity.Book;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDOM {

    private String file;

    public XMLDOM(String file) {
        this.file = file;
    }

    public List<Book> readXML() {
        List<Book> books = new ArrayList<>();
        Document document = loadDocument();

        NodeList nodeList = document.getElementsByTagName("obra");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Book b = new Book();

            Element bookElement = (Element) nodeList.item(i);

            Element isbnElement = (Element) bookElement.getElementsByTagName("isbn").item(0);
            String isbn = isbnElement.getTextContent();
            b.setISBN(isbn);

            Element titleElement = (Element) bookElement.getElementsByTagName("titulo").item(0);
            String title = titleElement.getTextContent();
            b.setTitle(title);

            Element categoryElement = (Element) bookElement.getElementsByTagName("categoria").item(0);
            String category = categoryElement.getTextContent();
            b.setCategory(category);

            Element dateElement = (Element) bookElement.getElementsByTagName("data").item(0);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(dateElement.getTextContent(), formatter);
                b.setDate(date);
            } catch (DateTimeParseException ex) {
                b.setDate(null);
            }

            Element editionElement = (Element) bookElement.getElementsByTagName("edicao").item(0);
            String edition = editionElement.getTextContent();
            b.setEdition(edition);

            Element publisherElement = (Element) bookElement.getElementsByTagName("editora").item(0);
            String publisher = publisherElement.getTextContent();
            b.setPublisher(publisher);

            NodeList authorsList = bookElement.getElementsByTagName("autor");
            String author;
            List<String> authors = new ArrayList<>();
            for (int j = 0; j < authorsList.getLength(); j++) {
                Element authorElement = (Element) authorsList.item(j);
                author = authorElement.getTextContent();
                authors.add(author);
            }
            b.setAuthors(authors);

            NodeList keywordsList = bookElement.getElementsByTagName("palavra-chave");
            String keyword;
            List<String> keywords = new ArrayList<>();
            for (int j = 0; j < keywordsList.getLength(); j++) {
                Element keywordElement = (Element) keywordsList.item(j);
                keyword = keywordElement.getTextContent();
                keywords.add(keyword);
            }
            b.setKeywords(keywords);

            books.add(b);
        }

        return books;
    }

    private Document loadDocument() {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //factory.setValidating(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }

        return document;
    }
}
