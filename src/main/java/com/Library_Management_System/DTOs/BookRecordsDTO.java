package com.Library_Management_System.DTOs;

public class BookRecordsDTO {
	private Long book_id;
	private String bookTitle;
	private String bookAuthor;
	public Long getBook_id() {
		return book_id;
	}
	public void setBook_id(Long book_id) {
		this.book_id = book_id;
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
	public BookRecordsDTO(Long book_id, String bookTitle, String bookAuthor) {
		super();
		this.book_id = book_id;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
	}
	
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
}
