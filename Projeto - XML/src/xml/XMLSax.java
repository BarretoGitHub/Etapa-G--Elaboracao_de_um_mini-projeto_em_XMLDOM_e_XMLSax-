package xml;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import entity.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

 
public class XMLSax extends DefaultHandler{
    
    private List<Book> books;
    private String value;
    private Book b;
    
    public List<Book> getBooks(){
        return books;
    }
    
    @Override
    public void startDocument() throws SAXException{
        books = new ArrayList<>();
    }
    
    @Override
    public void endDocument() throws SAXException{
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if(qName.equals("obra")){
            b = new Book();
            books.add(b);
        }
        else if(qName.equals("autores")){
            List<String> authors = new ArrayList<>();
            b.setAuthors(authors);
        }
        else if(qName.equals("palavras-chave")){
            List<String> keywords = new ArrayList<>();
            b.setKeywords(keywords);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equals("isbn")){
            b.setISBN(value);
        }
        else if(qName.equals("titulo")){
            b.setTitle(value);
        }
        else if(qName.equals("categoria")){
            b.setCategory(value);
        }
        else if(qName.equals("autor")){
            List<String> authors = b.getAuthors();
            authors.add(value);
        }
        else if(qName.equals("palavra-chave")){
            List<String> keywords = b.getKeywords();
            keywords.add(value);
        }
        else if(qName.equals("data")){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(value, formatter);
                b.setDate(date);
            }
            catch(DateTimeParseException ex){
                b.setDate(null);
            }
        }
        else if(qName.equals("edicao")){
            b.setEdition(value);
        }
        else if(qName.equals("editora")){
            b.setPublisher(value);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        value = String.valueOf(ch,start,length);
    }
}
