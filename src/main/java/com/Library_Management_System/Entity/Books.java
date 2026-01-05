package com.Library_Management_System.Entity;

import com.Library_Management_System.Enums.Categories;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long book_id;
	private String bookTitle;
	private String bookAuthor;
	@Column(unique = true)
	private String isbn;
	private boolean available=true;

	public Books() {
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public boolean getBook_availability() {
		return available;
	}

	public void setBook_availability(boolean book_availability) {
		this.available = book_availability;
	}

	public LocalDateTime getAdded_at() {
		return added_at;
	}

	public void setAdded_at(LocalDateTime added_at) {
		this.added_at = added_at;
	}

	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}

	@Enumerated(EnumType.STRING)
	private Categories category;

	public Books(Long book_id, String bookTitle, String bookAuthor, String isbn, Categories category,Boolean available,
			LocalDateTime added_at) {
		super();
		this.book_id = book_id;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.isbn = isbn;
		this.available = available;
		this.category = category;
		this.added_at = added_at;
	}

	private LocalDateTime added_at = LocalDateTime.now();

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

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

}
