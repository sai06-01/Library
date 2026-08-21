package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BookService;
import com.example.demo.model.Book;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Add book
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Search book by ID
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        return bookService.getBookById(bookId);
    }

    // Update availability
    @PutMapping("/{bookId}/availability")
    public Book updateAvailability(
            @PathVariable int bookId,
            @RequestParam boolean available) {

        return bookService.updateAvailability(bookId, available);
    }

    // Delete book
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable int bookId) {

        bookService.deleteBook(bookId);

        return "Book deleted successfully";
    }
}