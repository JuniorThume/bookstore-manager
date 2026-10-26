package bookstoremanager.controller;

import bookstoremanager.dto.BookDTO;
import bookstoremanager.dto.MessageResponseDTO;
import bookstoremanager.entity.Book;
import bookstoremanager.repository.BookRepository;
import bookstoremanager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid BookDTO book) {
        return bookService.create(book);
    }
}
