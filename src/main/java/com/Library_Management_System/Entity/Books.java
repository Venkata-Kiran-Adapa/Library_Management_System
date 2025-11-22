package com.Library_Management_System.Entity;

import com.Library_Management_System.Enums.Categories;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Books {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long book_id;
        private String bookTitle;
        private String bookAuthor;
        @Column(unique = true)
        private String isbn;
        private Long total_quantity;

    public Books() {
    }

    public Books(long available_quantity, Categories category, long total_quantity, String isbn, String book_author, String book_title, long book_id) {
        this.available_quantity = available_quantity;
        this.category = category;
        this.total_quantity = total_quantity;
        this.isbn = isbn;
        this.bookAuthor = book_author;
        this.bookTitle = book_title;
        this.book_id = book_id;
    }

    @Enumerated(EnumType.STRING)
        private Categories category;
        private long available_quantity;
        private LocalDateTime added_at=LocalDateTime.now();

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return bookTitle;
    }

    public void setBook_title(String book_title) {
        this.bookTitle = book_title;
    }

    public String getBook_author() {
        return bookAuthor;
    }

    public void setBook_author(String book_author) {
        this.bookAuthor = book_author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(long total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public long getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(long available_quantity) {
        this.available_quantity = available_quantity;
    }
}
