package com.Library_Management_System.Controllers;

import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Enums.Categories;
import com.Library_Management_System.Exception_Handling.NotFoundException;
import com.Library_Management_System.Repository.BooksRepo;
import com.Library_Management_System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.webauthn.api.PublicKeyCose;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BooksController {
    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public Optional<Books> getById(@PathVariable long id) throws NotFoundException {
        return bookService.getById(id);
    }


    @GetMapping("/isbn/{isbn}")
    public Optional<Books> getBookByIsBn(@PathVariable String isbn) throws NotFoundException {
        return bookService.getBookByIsBn(isbn);
    }
    
    
    @GetMapping("/category/{category}")
    public List<Books> getBooksByCategories(@PathVariable String category) {
        Categories categoryEnum;
        try {
            categoryEnum = Categories.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid category: " + category);
        }
        return bookService.getBooksByCategories(categoryEnum);
    }
    
    @GetMapping("/author/{author}")
    public List<Books> getBookByAuthor(@PathVariable String author) throws NotFoundException {
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("")
    public List<Books> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/availableBooks")
    public List<Books> getAllAvailableBooks(){
        return bookService.getAllAvailableBooks();
    }
    
    @Transactional
    @PostMapping("")
    public Books addBook(@RequestBody Books book){
        return bookService.addBook(book);
    }

    @Transactional
    @PatchMapping("/{id}")
    public Books modifyBook(@PathVariable long id ,@RequestBody Map<String,Object> partialBook)throws NotFoundException {
        return bookService.modifyBook(id,partialBook);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Optional<Books> deleteBookById(@PathVariable long id) throws NotFoundException {
        return bookService.deleteBookById(id);
    }
}
