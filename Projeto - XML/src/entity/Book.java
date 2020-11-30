package entity;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private String ISBN;
    private String title;
    private String category;
    private List<String> authors;
    private List<String> keywords;
    private LocalDate date;
    private String edition;
    private String publisher;
    
    @Override
    public String toString(){
        return String.format("ISBN: %s\nTitle: %s\nCategory: %s\nAuthors: %s\nKeywords: %s\n",ISBN,title,category,authors,keywords);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
