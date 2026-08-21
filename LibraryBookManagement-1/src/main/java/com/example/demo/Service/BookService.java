package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.BookNotFoundException;
import com.example.demo.model.Book;

@Service
public class BookService {

    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(101, "Java", "James Gosling", 500, true),
            new Book(102, "Python", "Guido van Rossum", 450, true),
            new Book(103, "Spring Boot", "Rod Johnson", 600, false),
            new Book(104, "Django", "Adrian Holovaty", 550, true),
            new Book(105, "C Programming", "Dennis Ritchie", 400, true)
    ));

    // Get all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Add book
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    // Search book by ID
    public Book getBookById(int bookId) {

        return books.stream()
                .filter(book -> book.getBookId() == bookId)
                .findFirst()
                .orElseThrow(() ->
                    new BookNotFoundException(
                        "Book ID not found : " + bookId));
    }

    // Update availability
    public Book updateAvailability(int bookId, boolean available) {

        Book book = getBookById(bookId);

        book.setAvailable(available);

        return book;
    }

    // Delete book
    public boolean deleteBook(int bookId) {

        Book book = getBookById(bookId);

        books.remove(book);

        return true;
    }
}