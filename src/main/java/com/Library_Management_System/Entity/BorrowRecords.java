package com.Library_Management_System.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.Library_Management_System.DTOs.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "borrow_records")
public class BorrowRecords {
	
	
    @Override
	public String toString() {
		return "BorrowRecords [id=" + borrow_records_id + ", user=" + user + ", book=" + book + ", issueDate=" + issueDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", fineAmount=" + fineAmount + "]";
	}

		@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long borrow_records_id;
        @ManyToOne
        @JoinColumn(name="user_id")
        @JsonIgnore
        private Users user;
        @ManyToOne
        private Books book;

    public BorrowRecords(Long id, Users user, Books book, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, long fineAmount) {
        this.borrow_records_id = id;
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
        return borrow_records_id;
    }

    public void setId(Long id) {
        this.borrow_records_id = id;
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


