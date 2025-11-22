package com.Library_Management_System.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.Library_Management_System.DTOs.UserDTO;

@Entity
@Table(name = "borrow_records")
public class BorrowRecords {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        private Users user;
        @ManyToOne
        private Books book;

    public BorrowRecords(Long id, Users user, Books book, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, long fineAmount) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    public BorrowRecords() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
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


