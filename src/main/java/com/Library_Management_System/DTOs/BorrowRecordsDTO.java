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
        private UserDTO user;
        @ManyToOne
        private Books book;

    public BorrowRecordsDTO(BorrowRecords borrowRecords) {
        this.id = borrowRecords.getId();
        this.user = new UserDTO(borrowRecords.getUser());
        this.book = borrowRecords.getBook();
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
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


