package pl.sda.library.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookCreateRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private List<String> authors;

    public BookCreateRequest() {
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
}
