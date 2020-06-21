package pl.sda.library.services;

import pl.sda.library.exceptions.BookNotFoundException;
import pl.sda.library.model.Book;
import pl.sda.library.model.BookCreateRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    List<Book> books = new ArrayList<>();

    public LibraryService() {
        books.add(new Book("The Art of Software Testing", Arrays.asList("Glenford J. Myers"," Corey Sandler"," Tom Badgett.")));
        books.add(new Book("Software Testing, 2nd Edition", Collections.singletonList("Ron Patton")));
        books.add(new Book("Software Testing: A Craftsman's Approach, Fourth Edition", Collections.singletonList("Paul C. Jorgensen")));
        books.add(new Book("How to Break Software: A Practical Guide to Testing", Collections.singletonList("James Whittaker")));
    }

    public List<Book> getBooks(String titleSearchPhrase, String authorSearchPhrase) {
        return books.stream()
                .filter(book -> Optional.ofNullable(titleSearchPhrase)
                        .map(searchPhrase -> book.getTitle().toLowerCase().contains(searchPhrase.toLowerCase()))
                        .orElse(true)
                )
                .filter(book -> Optional.ofNullable(authorSearchPhrase)
                        .map(searchPhrase -> book.getAuthors()
                                .stream()
                                .anyMatch(author -> author.toLowerCase().contains(searchPhrase.toLowerCase())))
                        .orElse(true)
                )
                .collect(Collectors.toList());
    }

    public Book addBook(BookCreateRequest bookCreateRequest) {
        Book book = new Book(bookCreateRequest.getTitle(), bookCreateRequest.getAuthors());
        books.add(book);

        return book;
    }

    public Book getBook(Integer id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public boolean removeBook(Integer id) {
       return books.removeIf(book -> book.getId().equals(id));
    }
}
