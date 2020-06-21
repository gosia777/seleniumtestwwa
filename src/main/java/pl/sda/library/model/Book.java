package pl.sda.library.model;

import java.util.List;

public class Book {
    private static Integer bookCounter = 0;
    private Integer id;
    private String title;
    private List<String> authors;

    public Book(String title, List<String> authors) {
        this.id = getNextId();
        this.title = title;
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    private static Integer getNextId() {
        return bookCounter++;
    }
}
