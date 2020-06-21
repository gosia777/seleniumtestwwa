package pl.sda.library.controllers;

import pl.sda.library.model.Book;
import pl.sda.library.services.LibraryService;
import pl.sda.library.model.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = LibraryController.CONTENT_TYPE)
public class LibraryController {
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    LibraryService libraryService;

    @GetMapping
    public List<Book> getBooks(@RequestParam(name = "title", required = false) String title,
                               @RequestParam(name = "author", required = false) String author) {
        return libraryService.getBooks(title, author);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return libraryService.getBook(id);
    }

    @PostMapping(consumes = CONTENT_TYPE)
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        Book newBook = libraryService.addBook(bookCreateRequest);

        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Integer id) {
        libraryService.removeBook(id);
    }
}
