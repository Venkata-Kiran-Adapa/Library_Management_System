package com.Library_Management_System.DTOs;

import java.util.List;
import java.util.ArrayList;

import com.Library_Management_System.Entity.Books;
import com.Library_Management_System.Entity.BorrowRecords;
import com.Library_Management_System.Entity.Reservation;
import com.Library_Management_System.Entity.Users;
import com.Library_Management_System.Enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class UserDTO {
    private Long user_id;
    private String user_name;
    private String userEmail;
    private Role role;
    private List<BorrowRecords> borrowRecords=new ArrayList<>();
    private List<Reservation> reservation=new ArrayList<>();
    private List<Books> books=new ArrayList<>();
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<BorrowRecords> getBorrowRecords() {
		return borrowRecords;
	}
	public void setBorrowRecords(List<BorrowRecords> borrowRecords) {
		this.borrowRecords = borrowRecords;
	}
	public List<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	public List<Books> getBooks() {
		return books;
	}
	public void setBooks(List<Books> books) {
		this.books = books;
	}
	public UserDTO(Users user) {
		super();
		this.user_id = user.getUser_id();
		this.user_name = user.getUser_name();
		this.userEmail = user.getUserEmail();
		this.role = user.getRole();
		this.borrowRecords = user.getBorrowRecords();
		this.reservation = user.getReservation();
		this.books = user.getBooks();
	}
	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", user_name=" + user_name + ", userEmail=" + userEmail + ", role="
				+ role + ", borrowRecords=" + borrowRecords + ", reservation=" + reservation + ", books=" + books + "]";
	}
	

}