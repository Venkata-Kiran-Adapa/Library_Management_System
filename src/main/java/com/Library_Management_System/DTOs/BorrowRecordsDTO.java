package com.Library_Management_System.DTOs;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.BorrowRecords;

public class BorrowRecordsDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        private UserRecordsDTO user;
        @ManyToOne
        private BookRecordsDTO book;

    public BorrowRecordsDTO(BorrowRecords borrowRecords) {
        this.id = borrowRecords.getId();
        this.user = new UserRecordsDTO(borrowRecords.getUser());
        this.book = new BookRecordsDTO(borrowRecords.getBook().getBook_id(), borrowRecords.getBook().getBook_title(),borrowRecords.getBook().getBook_author());
        this.issueDate = borrowRecords.getIssueDate();
        this.dueDate = borrowRecords.getDueDate();
        this.returnDate = borrowRecords.getReturnDate();
        this.fineAmount = borrowRecords.getFineAmount();
    }

    public BorrowRecordsDTO()  {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRecordsDTO getUser() {
        return user;
    }

    public void setUser(UserRecordsDTO user) {
        this.user = user;
    }

    public BookRecordsDTO getBook() {
        return book;
    }

    public void setBook(BookRecordsDTO book) {
        this.book = book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public long getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(long fineAmount) {
        this.fineAmount = fineAmount;
    }

    	private LocalDate issueDate;
        private LocalDate dueDate;
        private LocalDate returnDate;

        private long fineAmount;

}


